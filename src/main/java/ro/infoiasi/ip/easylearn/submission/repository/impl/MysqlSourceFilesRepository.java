package ro.infoiasi.ip.easylearn.submission.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.compiler.SourceFile;
import ro.infoiasi.ip.easylearn.submission.repository.utils.SourceFileMapper;
import ro.infoiasi.ip.easylearn.submission.repository.utils.SubmissionMapper;

import java.util.List;

@Repository
public class MysqlSourceFilesRepository implements ro.infoiasi.ip.easylearn.submission.repository.api.SourceFilesRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void saveAll(List <SourceFile> files){
        for (SourceFile source : files) {
            String insertFile = "insert into submission_code(submissionId, fileName, sourceCode) values (?,?,?)";
            jdbcTemplate.update(insertFile, source.getSubmissionId(), source.getFileName(), source.getContent());
        }
    }

    @Override
    public List <SourceFile> findBySubmissionId(Long submissionId) {
        String findAll = "SELECT * FROM submission_code where submissionId=?";
        return jdbcTemplate.query(findAll, new SourceFileMapper(), submissionId);
    }
}
