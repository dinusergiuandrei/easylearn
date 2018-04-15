package ro.infoiasi.ip.easylearn.submission.repository.api;

import ro.infoiasi.ip.easylearn.submission.model.Submission;
import java.util.List;


public interface SubmissionRepository {
    Long save(Submission submission);
    Submission findById(Long id);
    List<Submission> findAll();
    List<Submission> findByState(String state);
}
