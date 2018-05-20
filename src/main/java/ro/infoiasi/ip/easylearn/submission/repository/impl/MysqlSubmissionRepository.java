package ro.infoiasi.ip.easylearn.submission.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.compiler.SourceFile;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.submission.repository.utils.SubmissionMapper;
import ro.infoiasi.ip.easylearn.utils.RunState;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class MysqlSubmissionRepository implements SubmissionRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Submission submission) {
        String insertQuery = "insert into submissions values (NULL, ?, ?, ?, ?, ?, ?)"; // submission ID is auto_increment

        jdbcTemplate.update(insertQuery,
                submission.getUserId(),
                submission.getProblemId(),
                submission.getMainSource(),
                submission.getLanguage().toString(),
                new SimpleDateFormat("YYYY-MM-dd HH-MM-ss").format(submission.getDate()),
                submission.getState().toString());

        Long id = jdbcTemplate.queryForObject("select max(submissionID) from submissions;", Long.class);

        if (id == null) id = 0L; // or throw exception ???
        submission.setId(id);

        // submission.getRuns()); when we save a submission we must not have RUNS

        for (SourceFile source : submission.getSources()) {
            jdbcTemplate.update("insert into submissionsCode values (NULL, ?,?,?)",
                    submission.getId(),
                    source.getTitle(),
                    source.getContent());

            Long s_id = jdbcTemplate.queryForObject("select max(id) from submissionsCode;", Long.class);
            if (s_id == null) s_id = 0L; // or throw exception ???
            source.setId(s_id);
        }

        return submission.getId();
    }

    @Override
    public Submission findById(Long id) {
        String mysql = "SELECT * FROM submissions where submissionID='" + id + "'";
        List<Submission> submission = jdbcTemplate.query(mysql, new SubmissionMapper());
        return submission.get(1);
    }

    @Override
    public List<Submission> findAll() {
        String mysql = "SELECT * FROM submissions";
        return jdbcTemplate.query(mysql, new SubmissionMapper());
    }

    @Override
    public List<Submission> findByState(RunState state) {
        String mysql = "SELECT * FROM submissions where state='" + state.toString() + "'";
        return jdbcTemplate.query(mysql, new SubmissionMapper());
    }

    @Override
    public Long update(Submission submission) {



        return null;
    }
}
