package ro.infoiasi.ip.easylearn.submission.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import ro.infoiasi.ip.easylearn.execution.SubmissionRunner;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionResponse;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.submission.repository.impl.MapSubmissionRepository;
import ro.infoiasi.ip.easylearn.submission.validation.SubmissionValidator;

import java.util.LinkedList;
import java.util.List;


@Service
public class SubmissionService {
    private SubmissionRepository submissionRepository;
    private JmsTemplate jmsTemplate;
    private SubmissionValidator submissionValidator;

    public SubmissionService(SubmissionRepository submissionRepository, JmsTemplate jmsTemplate, SubmissionValidator submissionValidator) {
        this.submissionRepository = submissionRepository;
        this.jmsTemplate = jmsTemplate;
        this.submissionValidator = submissionValidator;
    }

    public SubmissionResponse submit(Submission submission){
        submissionValidator.validate(submission);

        submission.setState("waiting");
        Long id = submissionRepository.save(submission);

        // Send the id of the submission to be processed by the execution module.
        jmsTemplate.convertAndSend("submissionQueue", id);

        return new SubmissionResponse(id);
    }

    public List <Submission> findAll() {

        return submissionRepository.findAll();
    }

    public Submission findById(Long id) {

        return submissionRepository.findById(id);
    }
}
