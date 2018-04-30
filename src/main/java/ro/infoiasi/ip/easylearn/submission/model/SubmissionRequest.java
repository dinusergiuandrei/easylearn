package ro.infoiasi.ip.easylearn.submission.model;


import ro.infoiasi.ip.easylearn.utils.Language;

public class SubmissionRequest {
    private Long problemId;
    private Language language;
    private String sourceCode;

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

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    @Override
    public String toString() {
        return "SubmissionRequest{" +
                "problemId=" + problemId +
                ", language='" + language + '\'' +
                ", sourceCode='" + sourceCode + '\'' +
                '}';
    }
}
