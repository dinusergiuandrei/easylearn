package ro.infoiasi.ip.easylearn.submission.repository.utils;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ro.infoiasi.ip.easylearn.submission.model.Run;
import ro.infoiasi.ip.easylearn.utils.RunState;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RunMapper implements RowMapper <Run> {
    @Nullable
    @Override
    public Run mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Run run = new Run();
        run.setId(resultSet.getLong("id"));
        run.setSubmissionId(resultSet.getLong("submissionId"));
        run.setTestId(resultSet.getLong("testId"));
        run.setRunTimeMs(resultSet.getLong("runTimeMS"));
        run.setMemoryBytes(resultSet.getLong("memoryBytes"));
        run.setStatus(RunState.valueOf(resultSet.getString("status")));

        return run;
    }
}