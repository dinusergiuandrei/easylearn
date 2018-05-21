package ro.infoiasi.ip.easylearn.submission.repository.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.utils.Language;
import ro.infoiasi.ip.easylearn.utils.SubmissionState;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubmissionMapper implements RowMapper <Submission> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Nullable
    @Override
    public Submission mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Submission submission = new Submission();
        submission.setId(resultSet.getLong("submissionID"));
        submission.setUserId(resultSet.getLong("userID"));
        submission.setProblemId(resultSet.getLong("problemId"));
        submission.setMainSource(resultSet.getString("main_file"));
        submission.setLanguage(Language.valueOf(resultSet.getString("lang")));
        submission.setDate(resultSet.getDate("submitted_at"));
        submission.setState(SubmissionState.valueOf(resultSet.getString("state")));

        return submission;
    }
}

