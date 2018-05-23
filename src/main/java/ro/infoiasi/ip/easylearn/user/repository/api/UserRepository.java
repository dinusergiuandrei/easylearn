package ro.infoiasi.ip.easylearn.user.repository.api;

import ro.infoiasi.ip.easylearn.user.model.User;

import java.util.List;

public interface UserRepository {
    boolean update(User user);
    User findById(Long id);
    Long findByEmail(String email);
    List<User> findAll();
    Long register(User user);
    boolean login(String email, String password);
    boolean delete(Long id);
}
