package ro.infoiasi.ip.easylearn.submission.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.submission.model.Run;
import ro.infoiasi.ip.easylearn.submission.repository.api.RunRepository;
import ro.infoiasi.ip.easylearn.submission.repository.utils.RunMapper;

import java.util.List;

@Repository
public class MysqlRunRepository implements RunRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(Run run) {
        String insertQuery = "insert into runs (id, submission_id,memory_bytes,run_time_ms,status) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update( insertQuery, run.getId(), run.getSubmissionId(), run.getMemoryBytes(),run.getRunTimeMs(),run.getStatus());
    }

    @Override
    public List <Run> findBySubmissionId(Long submissionId) {
        String mysql = "SELECT * FROM runs where submissionId='" + submissionId + "'";
        List <Run> runs = jdbcTemplate.query(mysql, new RunMapper());
        return runs;
    }
}