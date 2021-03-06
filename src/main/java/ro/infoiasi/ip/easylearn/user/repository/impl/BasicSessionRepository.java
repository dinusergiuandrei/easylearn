package ro.infoiasi.ip.easylearn.user.repository.impl;

import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.user.exception.NotLoggedInException;
import ro.infoiasi.ip.easylearn.user.repository.api.SessionRepository;
import ro.infoiasi.ip.easylearn.utils.CookieManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Repository
public class BasicSessionRepository implements SessionRepository {
    private Map<String, Long> sessions; //UUID and USER_ID

    public BasicSessionRepository() {
        sessions = new HashMap<>();
    }

    @Override
    public String create(Long userID) {
        String sid = UUID.randomUUID().toString();
        sessions.put(sid,userID);
        return sid;
    }

    @Override
    public void destroy(String sid) {
        sessions.remove(sid);
    }

    @Override
    public boolean isActive(String sid) {
        return sessions.containsKey(sid);
    }

    @Override
    public Long getUserID(String sid) {
        return sessions.get(sid);
    }

    public Long MustBeLoggedIn(HttpServletRequest request) {
        Cookie cookie = CookieManager.getCookie(request.getCookies(), "sid");
        if (cookie != null && this.isActive(cookie.getValue()))
            return this.getUserID(cookie.getValue());
        else throw new NotLoggedInException();
    }
}
