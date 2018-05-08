package ro.infoiasi.ip.easylearn.submission.repository.utils;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.utils.Language;
import ro.infoiasi.ip.easylearn.utils.SubmissionState;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubmissionMapper implements RowMapper<Submission> {
    @Nullable
    @Override
    public Submission mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Submission submission = new Submission();
        submission.setId(resultSet.getLong("id"));
        //TODO : implement according to database specification
        submission.setLanguage(Language.valueOf(resultSet.getString("language")));
        submission.setState(SubmissionState.valueOf(resultSet.getString("submissionState")));
        return submission;
    }
}
