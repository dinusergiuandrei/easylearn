package ro.infoiasi.ip.easylearn.submission.model;


public class Run {
    private Long id;
    private Long submissionId;
    private Long runTimeMs;
    private Long memoryBytes;
    // fail, success
    private String status;

    public Run() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public Long getRunTimeMs() {
        return runTimeMs;
    }

    public void setRunTimeMs(Long runTimeMs) {
        this.runTimeMs = runTimeMs;
    }

    public Long getMemoryBytes() {
        return memoryBytes;
    }

    public void setMemoryBytes(Long memoryBytes) {
        this.memoryBytes = memoryBytes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
