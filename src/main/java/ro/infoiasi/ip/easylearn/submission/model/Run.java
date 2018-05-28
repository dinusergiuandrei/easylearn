package ro.infoiasi.ip.easylearn.submission.model;


import ro.infoiasi.ip.easylearn.compiler.Output;
import ro.infoiasi.ip.easylearn.utils.RunState;

public class Run {
    private Long id;
    private Long submissionId;
    private Long testId;
    private Long runTimeMs;
    private Long memoryBytes;
    private RunState status;
    private String output;

    public Run() {
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        if (output != null && output.length() > 150)
            this.output = output.substring(0, 100);
        else
            this.output = output;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
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

    public RunState getStatus() {
        return status;
    }

    public void setStatus(RunState status) {
        this.status = status;
    }

}
