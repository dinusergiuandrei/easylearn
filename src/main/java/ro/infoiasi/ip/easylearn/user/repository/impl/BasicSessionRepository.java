package ro.infoiasi.ip.easylearn.user.repository.impl;

import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.user.repository.api.SessionRepository;

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


}
