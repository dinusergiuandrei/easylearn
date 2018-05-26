package ro.infoiasi.ip.easylearn.submission.repository.api;

import ro.infoiasi.ip.easylearn.compiler.SourceFile;

import java.util.List;

public interface SourceFilesRepository {
    void saveAll(List <SourceFile> files);

    List<SourceFile> findBySubmissionId(Long submissionId);
}
