package ro.infoiasi.ip.easylearn.submission.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.submission.repository.utils.SubmissionMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class MysqlSubmissionRepository implements SubmissionRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Submission submission) {
        String insertQuery = "insert into submissions values (NULL, ?, ?, ?, ?, ?)"; // submission ID is auto_increment
        jdbcTemplate.update(insertQuery,
                submission.getUserId(),
                submission.getProblemId(),
                submission.getMainSource(),
                submission.getLanguage().toString(),
                new SimpleDateFormat("YYYY-MM-DD HH-MM-SS").format(submission.getDate()),
                submission.getState().toString());

        Long id = null;
        jdbcTemplate.queryForObject(
                "select max(submissionID) from submissions;",
                new Object[]{id}, Long.class);

        if (id == null) id = 0L; // or throw exception ???
        submission.setId(id);

        // submission.getRuns()); when we save a submission we must not have RUNS

        return submission.getId();
    }

    @Override
    public Submission findById(Long id) {
        String mysql = "SELECT * FROM submissions where id='" + id + "'";
        List<Submission> submission = jdbcTemplate.query(mysql, new SubmissionMapper());
        System.out.println(submission);
        return submission.get(1);
    }

    @Override
    public List<Submission> findAll() {
        String mysql = "SELECT * FROM submissions";
        List<Submission> submissions = jdbcTemplate.query(mysql, new SubmissionMapper());
        return submissions;
    }

    @Override
    public List<Submission> findByState(String state) {
        String mysql = "SELECT * FROM submissions where state='" + state + "'";
        List<Submission> submissions = jdbcTemplate.query(mysql, new SubmissionMapper());
        return submissions;

    }

    @Override
    public Long update(Submission submission) {
        return null;
    }
}
