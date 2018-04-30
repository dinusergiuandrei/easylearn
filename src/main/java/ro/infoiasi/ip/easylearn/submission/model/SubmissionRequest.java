package ro.infoiasi.ip.easylearn.submission.model;


public class SubmissionRequest {
    private Long problemId;
    private String language;
    private String sourceCode;

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
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
