package ro.infoiasi.ip.easylearn.user.repository.api;

import ro.infoiasi.ip.easylearn.user.model.User;

import java.util.List;

public interface UserRepository {
    Long save(User submission);
    User findById(Long id);
    List<User> findAll();
    Integer getTotalUsers();
    boolean register(User user);
    String getLastId();
    boolean login(String email, String password);
}
