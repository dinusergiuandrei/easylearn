package ro.infoiasi.ip.easylearn.management.repository.api;

import ro.infoiasi.ip.easylearn.management.model.SubmissionTest;

import java.util.List;

public interface SubmissionTestRepository {
    Long save(SubmissionTest submission);
    SubmissionTest findById(Long id);
    List<SubmissionTest> findAll();
    List<SubmissionTest> findAllForSubmission(int submissionID);
    List<SubmissionTest> findTestsForSubmissionByStatus(int submissionID, String status);
}
