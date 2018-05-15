package ro.infoiasi.ip.easylearn.submission.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.submission.repository.utils.SubmissionMapper;

import java.util.*;

public class MysqlSubmissionRepository implements SubmissionRepository{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Submission submission) {
        String insertQuery = "insert into submissions values (?, ?, ?, ?, ?)";
        jdbcTemplate.update( insertQuery, submission.getId(),submission.getProblemId(), submission.getLanguage(),submission.getMainSource(),submission.getState(),submission.getRuns(),submission.getResult());
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
    public List <Submission> findByState(String state) {
        String mysql = "SELECT * FROM submissions where state='" + state + "'";
        List<Submission> submissions = jdbcTemplate.query(mysql, new SubmissionMapper());
        return submissions;

    }
}
