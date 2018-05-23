package ro.infoiasi.ip.easylearn.compiler;

public class SourceFile {
    private Long id;
    private Long submissionId;
    private String fileName;
    private String content;

    public SourceFile(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
    }

    public SourceFile() {
        this.fileName = "";
        this.content ="";
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

    public String getFileName() {
        return fileName;
    }

    public String getContent() {
        return content;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
