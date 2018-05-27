package ro.infoiasi.ip.easylearn.user.repository.impl;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.user.model.User;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;
import ro.infoiasi.ip.easylearn.user.repository.utils.UserMapper;

import java.sql.Types;
import java.util.List;

// this is now correctly implemented => do not change

@Repository
public class SqlUserRepository implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean update(User user) {

        String pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        if (user.getName().length() < 3 || user.getName() == null)
            return false;
        if (user.getFirstName().length() < 3 || user.getFirstName() == null)
            return false;
        if ((!user.getEmail().matches(pattern)) || user.getEmail() == null)
            return false;
        if (user.getPassword().length() < 4 || user.getPassword() == null)
            return false;
        try {
            String query = "UPDATE users set name=?, firstName=?, password=?, email=? where id=?";
            Object[] params = new Object[]{user.getName(), user.getFirstName(), Hashing.sha256().hashUnencodedChars(user.getPassword()).toString(), user.getEmail(), user.getId()};
            int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
            jdbcTemplate.update(query, params, types);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public User findById(Long id) {
        String selectUserById = "SELECT * FROM users where id=?";
        List<User> users = jdbcTemplate.query(selectUserById, new UserMapper(), id);

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public Long findByEmail(String email) {
        String query = "SELECT * FROM users where email=?";
        List<User> users = jdbcTemplate.query(query, new UserMapper(), email);

        return users.isEmpty() ? null : users.get(0).getId();
    }

    @Override
    public List<User> findAll() {
        String selectAllUsers = "SELECT * FROM users";
        return jdbcTemplate.query(selectAllUsers, new UserMapper());
    }

    @Override
    public Long register(User user) {
        String pattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        if (user.getName().length() < 3 || user.getName() == null)
            return null;
        if (user.getFirstName().length() < 3 || user.getFirstName() == null)
            return null;
        if ((!user.getEmail().matches(pattern)) || user.getEmail() == null)
            return null;
        if (user.getPassword().length() < 4 || user.getPassword() == null)
            return null;
        if (user.getSecretAnswer().length() < 4 || user.getSecretAnswer() == null)
            return null;
        if (user.getSecretQuestion().length() < 4 || user.getSecretQuestion() == null)
            return null;
        if (isAvailableEmail(user.getEmail())) {
            String query = "INSERT INTO users (name, firstName, email, password, secretAnswer, secretQuestion) VALUES (?,?,?,?,?,?)";
            Object[] params = new Object[]{user.getName(), user.getFirstName(), user.getEmail(), Hashing.sha256().hashUnencodedChars(user.getPassword()).toString(), user.getSecretAnswer(), user.getSecretQuestion()};
            int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

            jdbcTemplate.update(query, params, types);

            return findByEmail(user.getEmail());
        } else {
            return null;
        }
    }

    private boolean isAvailableEmail(String email) {
        return findByEmail(email) == null;
    }

    @Override
    public boolean login(String email, String password) {
        String query = "SELECT * FROM users WHERE email=? AND password=?";
        List<User> users = jdbcTemplate.query(query, new UserMapper(), email, Hashing.sha256().hashUnencodedChars(password).toString());

        return users.size() > 0;
    }

    @Override
    public boolean delete(Long id) {
        String query = "DELETE FROM users WHERE id=?";
        return jdbcTemplate.update(query, id) > 0;
    }
}


