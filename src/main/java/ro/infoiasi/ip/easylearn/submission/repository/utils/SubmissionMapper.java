package ro.infoiasi.ip.easylearn.submission.repository.utils;

import org.glassfish.jersey.server.filter.HttpMethodOverrideFilter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ro.infoiasi.ip.easylearn.compiler.SourceFile;
import ro.infoiasi.ip.easylearn.submission.model.Run;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.utils.Language;
import ro.infoiasi.ip.easylearn.utils.SubmissionState;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SubmissionMapper implements RowMapper<Submission> {
    @Nullable
    @Override
    public Submission mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Submission submission = new Submission();
        submission.setId(resultSet.getLong("id"));
        submission.setProblemId(resultSet.getLong("problemId"));
        //TODO : implement according to database specification
        submission.setLanguage(Language.valueOf(resultSet.getString("language")));

        List<SourceFile> sources = new LinkedList<>();
        sources.add((SourceFile) resultSet.getObject("sources"));
        submission.setSources(sources);

        submission.setState(SubmissionState.valueOf(resultSet.getString("submissionState")));

        List<Run> runs = new LinkedList<>();
        runs.add((Run)resultSet.getObject("runs"));
        submission.setRuns(runs);

        return submission;
    }
}

