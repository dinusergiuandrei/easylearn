package ro.infoiasi.ip.easylearn.management.model;

public class ProblemResponse {
    private String message;
    private String uri;

    public ProblemResponse(Long id) {
        message = "Processed problem with id: " + id;
        uri = "http://localhost:8100/problems/" + id;
    }

    public String getMessage() {
        return message;
    }

    public String getUri() {
        return uri;
    }
}
