package ro.infoiasi.ip.easylearn.submission.model;


import ro.infoiasi.ip.easylearn.compiler.SourceFile;
import ro.infoiasi.ip.easylearn.utils.Language;

import java.util.List;

public class SubmissionRequest {
    private Long problemId;
    private Long userID;
    private Language language;
    private String mainSource;
    private List<SourceFile> sources;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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
                ", language='" + language + '\'' +
                ", sources='" + sources + '\'' +
                '}';
    }
}
