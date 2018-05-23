package ro.infoiasi.ip.easylearn.user.model;

public class UserResponse {
    private String message;
    private String uri;

    public UserResponse(Long id) {
        message = "Processed user with id: " + id;
        uri = "http://localhost:8100/users/" + id;
    }

    public String getMessage() {
        return message;
    }

    public String getUri() {
        return uri;
    }
}
