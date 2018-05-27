package ro.infoiasi.ip.easylearn.submission.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.compiler.SourceFile;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.submission.repository.utils.SubmissionMapper;
import ro.infoiasi.ip.easylearn.utils.RunState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import static ro.infoiasi.ip.easylearn.utils.Language.Java;

@Repository
public class MysqlSubmissionRepository implements SubmissionRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Submission submission) {
        String insertQuery = "insert into submissions(userId, problemId, mainSource, language, date, state) values (?, ?, ?, ?, ?, ?)";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps =
                        connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, submission.getUserId());
                ps.setLong(2, submission.getProblemId());
                ps.setString(3, submission.getMainSource());
                ps.setString(4, submission.getLanguage().toString());
                ps.setDate(5, new java.sql.Date(submission.getDate().getTime()));
                ps.setString(6, submission.getState().toString());

                return ps;
            }
        };

        // obtain last generated id
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Submission findById(Long id) {
        String findById = "SELECT * FROM submissions where id=?";
        List <Submission> submission = jdbcTemplate.query(findById, new SubmissionMapper(), id);
        return submission.get(0);
    }

    @Override
    public List <Submission> findAll() {
        String findAll = "SELECT * FROM submissions";
        return jdbcTemplate.query(findAll, new SubmissionMapper());
    }

    @Override
    public List <Submission> findByState(RunState state) {
        String findByState = "SELECT * FROM submissions where state=?";
        return jdbcTemplate.query(findByState, new SubmissionMapper(), state.toString());
    }

    @Override
    public void update(Submission submission) {
        String updateQuery = "update submissions set userId=?, problemId=?, mainSource=?, language=?, date=?, state=? where id=?";
        jdbcTemplate.update(updateQuery, submission.getUserId(), submission.getProblemId(), submission.getMainSource(), submission.getLanguage().toString(), submission.getDate(), submission.getState().toString(), submission.getId());
    }
}
