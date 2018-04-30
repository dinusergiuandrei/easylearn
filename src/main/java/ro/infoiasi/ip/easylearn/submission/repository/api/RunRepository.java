package ro.infoiasi.ip.easylearn.submission.repository.api;


import ro.infoiasi.ip.easylearn.submission.model.Run;

import java.util.List;

public interface RunRepository {
    // TODO: create methods
    void save(Run run);
    List<Run> findBySubmissionId(Long submissionId);
}
