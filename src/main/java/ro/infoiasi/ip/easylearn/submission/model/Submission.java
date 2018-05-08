package ro.infoiasi.ip.easylearn.submission.model;

import ro.infoiasi.ip.easylearn.utils.Language;
import ro.infoiasi.ip.easylearn.utils.SubmissionState;

import java.util.List;

public class Submission {
    private Long id;
    private Long problemId;
    private Language language;
    private String sourceCode;
    private SubmissionState state;
    private List<Run> runs;
    private String result;

    public Submission() {
    }

    public Submission(Long problemId, Language language, String sourceCode) {
        this.problemId = problemId;
        this.language = language;
        this.sourceCode = sourceCode;
        this.state = SubmissionState.Waiting;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Run> getRuns() {
        return runs;
    }

    public void setRuns(List<Run> runs) {
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

    public SubmissionState getState() {
        return state;
    }

    public void setState(SubmissionState state) {
        this.state = state;
    }

    public static Submission constructSubmissionFrom(SubmissionRequest submissionRequest) {
        return new Submission(
                submissionRequest.getProblemId(),
                submissionRequest.getLanguage(),
                submissionRequest.getSourceCode()
        );
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
