package ro.infoiasi.ip.easylearn.user.repository.impl;

import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.user.repository.api.SessionRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Repository
public class BasicSessionRepository implements SessionRepository {
    private List <String> sessions;

    public BasicSessionRepository() {
        sessions = new LinkedList <>();
    }

    @Override
    public String create() {
        String sid = UUID.randomUUID().toString();
        sessions.add(sid);

        return sid;
    }

    @Override
    public void destroy(String sid) {
        sessions.remove(sid);
    }

    @Override
    public boolean isActive(String sid) {
        return sessions.contains(sid);
    }
}
