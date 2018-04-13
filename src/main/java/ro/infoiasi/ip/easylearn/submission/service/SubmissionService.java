package ro.infoiasi.ip.easylearn.submission.service;

import org.springframework.stereotype.Service;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionResponse;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.submission.repository.impl.MapSubmissionRepository;

import java.util.LinkedList;
import java.util.List;


@Service
public class SubmissionService {
    private SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public SubmissionResponse submit(Submission submission){
        submission.state = "waiting";
        submissionRepository.save(submission);

        SubmissionResponse response = new SubmissionResponse();
        response.result = "Processed sumbission with id: " + submission.id;

        return response;
    }

    public List <Submission> findAll() {
        return submissionRepository.findAll();
    }

    public Submission findById(Long id) {
        return submissionRepository.findById(id);
    }
}
