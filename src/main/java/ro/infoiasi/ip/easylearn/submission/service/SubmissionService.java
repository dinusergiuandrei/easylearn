package ro.infoiasi.ip.easylearn.submission.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ro.infoiasi.ip.easylearn.submission.model.Run;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionRequest;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionResponse;
import ro.infoiasi.ip.easylearn.submission.repository.api.RunRepository;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.submission.validation.SubmissionValidator;

import java.util.List;


@Service
public class SubmissionService {
    private SubmissionRepository submissionRepository;
    private RunRepository runRepository;
    private JmsTemplate jmsTemplate;
    private SubmissionValidator submissionValidator;

    public SubmissionService(SubmissionRepository submissionRepository, RunRepository runRepository, JmsTemplate jmsTemplate, SubmissionValidator submissionValidator) {
        this.submissionRepository = submissionRepository;
        this.runRepository = runRepository;
        this.jmsTemplate = jmsTemplate;
        this.submissionValidator = submissionValidator;
    }

    public SubmissionResponse submit(SubmissionRequest submissionRequest){
        submissionValidator.validate(submissionRequest);

        Submission submission = Submission.constructSubmissionFrom(submissionRequest);
        Long id = submissionRepository.save(submission);

        // Send the id of the submissionRequest to be processed by the execution module.
        jmsTemplate.convertAndSend("submissionQueue", id);

        return new SubmissionResponse(id);
    }

    public List <Submission> findAll() {
        List<Submission> submissions = submissionRepository.findAll();

        for(Submission submission : submissions){
            List<Run> runs = runRepository.findBySubmissionId(submission.getId());
            submission.setRuns(runs);
        }

        return submissions;
    }

    public Submission findById(Long id) {
        Submission submission = submissionRepository.findById(id);
        List<Run> runs = runRepository.findBySubmissionId(id);

        submission.setRuns(runs);

        return submission;
    }
}
