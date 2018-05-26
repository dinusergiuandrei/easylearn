package ro.infoiasi.ip.easylearn.management.model;

public class ProblemResponse {
    private String message;
    private String uri;
    private Long id;

    public ProblemResponse(Long id) {
        this.message = "Processed problem with id: " + id;
        this.uri = "http://localhost:8100/problems/" + id;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public String getUri() {
        return uri;
    }

    public Long getId() {
        return id;
    }
}
