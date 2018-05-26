package ro.infoiasi.ip.easylearn.user.repository.api;

public interface SessionRepository {
    void destroy(String sid);
    String create(Long userID);
    boolean isActive(String sid);
    Long getUserID(String sid);
}
