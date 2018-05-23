package ro.infoiasi.ip.easylearn.submission.repository.utils;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ro.infoiasi.ip.easylearn.compiler.SourceFile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SourceFileMapper implements RowMapper<SourceFile> {
    @Nullable
    @Override
    public SourceFile mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        SourceFile source = new SourceFile();

        source.setId(resultSet.getLong("id"));

        source.setSubmissionId(resultSet.getLong("submissionId"));

        source.setFileName(resultSet.getString("fileName"));

        source.setContent(resultSet.getString("sourceCode"));

        return source;
    }
}
