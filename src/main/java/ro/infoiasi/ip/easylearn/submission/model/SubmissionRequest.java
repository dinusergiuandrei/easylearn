package ro.infoiasi.ip.easylearn.submission.model;


import ro.infoiasi.ip.easylearn.compiler.SourceFile;
import ro.infoiasi.ip.easylearn.utils.Language;

import java.util.List;

public class SubmissionRequest {
    private Long problemId;
    private Long userId;
    private Language language;
    private String mainSource;
    private List<SourceFile> sources;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setMainSource(String mainSource) {
        this.mainSource = mainSource;
    }

    public String getMainSource() {
        return mainSource;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<SourceFile> getSources() {
        return sources;
    }

    public void setSources(List<SourceFile> sources) {
        this.sources = sources;
    }

    @Override
    public String toString() {
        return "SubmissionRequest{" +
                "problemId=" + problemId +
                "userId=" + userId +
                "mainSource=" + mainSource +
                ", language='" + language + '\'' +
                ", sources='" + sources + '\'' +
                '}';
    }
}
