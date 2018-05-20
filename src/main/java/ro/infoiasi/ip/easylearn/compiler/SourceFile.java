package ro.infoiasi.ip.easylearn.compiler;

public class SourceFile {
    private Long id;
    private Long submissionID;
    private String title;
    private String content;

    public SourceFile(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public SourceFile()
    {
        this.title = "";
        this.content ="";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(Long submissionID) {
        this.submissionID = submissionID;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
