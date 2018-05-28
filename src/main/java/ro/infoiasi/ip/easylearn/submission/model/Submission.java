package ro.infoiasi.ip.easylearn.submission.model;

import ro.infoiasi.ip.easylearn.compiler.SourceFile;
import ro.infoiasi.ip.easylearn.utils.Language;
import ro.infoiasi.ip.easylearn.utils.SubmissionState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Submission {
    private Long id;
    private Long problemId;
    private Long userId;
    private Language language;
    private List<SourceFile> sources;
    private String mainSource;
    private SubmissionState state;
    private List<Run> runs;
    private Date date;
    private String compileOut;

    public Submission() {
        sources = new ArrayList<>();
        runs = new ArrayList<>();
    }

    public Submission(Long problemId, Long userId, Language language, List<SourceFile> sources, String mainSource) {
        this.problemId = problemId;
        this.language = language;
        this.sources = sources;
        this.state = SubmissionState.Waiting;
        this.mainSource = mainSource;
        this.date = new Date();
        this.runs = new ArrayList<>();
        this.userId = userId;
    }

    public static Submission constructSubmissionFrom(SubmissionRequest submissionRequest) {
        return new Submission(
                submissionRequest.getProblemId(),
                submissionRequest.getUserId(),
                submissionRequest.getLanguage(),
                submissionRequest.getSources(),
                submissionRequest.getMainSource()
        );
    }


    public String getCompileOut() {
        return compileOut;
    }

    public void setCompileOut(String compileOut) {
        if (compileOut != null && compileOut.length() > 450)
            this.compileOut = compileOut.substring(0,450);
        else
            this.compileOut = compileOut;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getMainSource() {
        return mainSource;
    }

    public void setMainSource(String mainSource) {
        this.mainSource = mainSource;
    }


    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", problemId=" + problemId +
                ", userId=" + userId +
                ", language=" + language +
                ", sources=" + sources +
                ", mainSource='" + mainSource + '\'' +
                ", state=" + state +
                ", runs=" + runs +
                ", date=" + date +
                '}';
    }
}
