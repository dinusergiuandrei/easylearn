package ro.infoiasi.ip.easylearn.submission.model;


public class SubmissionResponse {
    private String message;
    private String uri;

    public SubmissionResponse(Long id) {
        message = "Processed submission with id: " + id;
        uri = "http://localhost:8100/submissions/" + id;
    }

    public String getMessage() {
        return message;
    }

    public String getUri() {
        return uri;
    }
}
