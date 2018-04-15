package ro.infoiasi.ip.easylearn.submission.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import ro.infoiasi.ip.easylearn.execution.SubmissionRunner;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionResponse;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.submission.repository.impl.MapSubmissionRepository;

import java.util.LinkedList;
import java.util.List;


@Service
public class SubmissionService {
    private SubmissionRepository submissionRepository;
    private JmsTemplate jmsTemplate;

    public SubmissionService(SubmissionRepository submissionRepository, JmsTemplate jmsTemplate) {
        this.submissionRepository = submissionRepository;
        this.jmsTemplate = jmsTemplate;
    }

    public SubmissionResponse submit(Submission submission){
        submission.state = "waiting";
        Long id = submissionRepository.save(submission);

        //TODO: send submission for processing (Queue)
        jmsTemplate.convertAndSend("submissionQueue", id);

        SubmissionResponse response = new SubmissionResponse();
        response.result = "Processed submission http://localhost:8100/submissions/" + id;

        return response;
    }

    public List <Submission> findAll() {
        return submissionRepository.findAll();
    }

    public Submission findById(Long id) {
        return submissionRepository.findById(id);
    }
}
