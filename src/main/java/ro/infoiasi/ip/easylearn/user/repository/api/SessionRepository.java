package ro.infoiasi.ip.easylearn.user.repository.api;

import javax.servlet.http.HttpServletRequest;

public interface SessionRepository {
    void destroy(String sid);
    String create(Long userID);
    boolean isActive(String sid);
    Long getUserID(String sid);
    Long MustBeLoggedIn(HttpServletRequest request);
}
