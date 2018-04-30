package ro.infoiasi.ip.easylearn.submission.model;


import java.util.List;

public class Submission {
    private Long id;
    private Long problemId;
    private String language;
    private String sourceCode;
    // waiting, evaluating, compilation failed, completed
    private String state;
    private List<Run> runs;

    public List <Run> getRuns() {
        return runs;
    }

    public void setRuns(List <Run> runs) {
        this.runs = runs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static Submission constructSubmissionFrom(SubmissionRequest submissionRequest) {
        Submission submission = new Submission();
        submission.setProblemId(submissionRequest.getProblemId());
        submission.setLanguage(submissionRequest.getLanguage());
        submission.setSourceCode(submissionRequest.getSourceCode());
        submission.setState("waiting");

        return submission;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", problemId=" + problemId +
                ", language='" + language + '\'' +
                ", sourceCode='" + sourceCode + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
