package ro.infoiasi.ip.easylearn.submission.model;

import ro.infoiasi.ip.easylearn.compiler.SourceFile;
import ro.infoiasi.ip.easylearn.utils.Language;
import ro.infoiasi.ip.easylearn.utils.SubmissionState;

import java.util.List;

public class Submission {
    private Long id;
    private Long problemId;
    private Language language;
    private List<SourceFile> sources;
    private SourceFile mainSource;
    private SubmissionState state;
    private List<Run> runs;
    private String result;

    public Submission() {
    }

    public Submission(Long problemId, Language language, List<SourceFile> sources, SourceFile mainSource) {
        this.problemId = problemId;
        this.language = language;
        this.sources = sources;
        this.state = SubmissionState.Waiting;
        this.mainSource = mainSource;
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

    public List<SourceFile> getSources() {
        return sources;
    }

    public void setSources(List<SourceFile> sources) {
        this.sources = sources;
    }

    public SubmissionState getState() {
        return state;
    }

    public void setState(SubmissionState state) {
        this.state = state;
    }

    public SourceFile getMainSource() {
        return mainSource;
    }

    public static Submission constructSubmissionFrom(SubmissionRequest submissionRequest) {
        return new Submission(
                submissionRequest.getProblemId(),
                submissionRequest.getLanguage(),
                submissionRequest.getSources(),
                submissionRequest.getMainSource()
        );
    }

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", problemId=" + problemId +
                ", language='" + language + '\'' +
                ", sources='" + sources + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
