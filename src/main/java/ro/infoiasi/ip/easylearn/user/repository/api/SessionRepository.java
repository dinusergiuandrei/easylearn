package ro.infoiasi.ip.easylearn.user.repository.api;

public interface SessionRepository {
    void destroy(String sid);
    String create();
    boolean isActive(String sid);
}
