package ro.infoiasi.ip.easylearn.submission.model;


public class SubmissionResponse {
    private String message;
    private String uri;
    private Long id;

    public SubmissionResponse(Long id) {
        message = "Processed submission with id: " + id;
        uri = "http://localhost:4200/api/submissions/" + id;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getUri() {
        return uri;
    }
}
