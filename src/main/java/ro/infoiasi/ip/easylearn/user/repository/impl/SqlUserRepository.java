package ro.infoiasi.ip.easylearn.user.repository.impl;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.user.model.ForgotUser;
import ro.infoiasi.ip.easylearn.user.model.User;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;
import ro.infoiasi.ip.easylearn.user.repository.utils.UserMapper;
import ro.infoiasi.ip.easylearn.utils.RandomString;

import java.sql.Types;
import java.util.List;

// this is now correctly implemented => do not change

@Repository
public class SqlUserRepository implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean update(User user) {
        if (user.getPassword() == null) {
            try {
                String query = "UPDATE users set name=?, firstName=?, email=? where id=?";
                Object[] params = new Object[]{user.getName(), user.getFirstName(), user.getEmail(), user.getId()};
                int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
                jdbcTemplate.update(query, params, types);

                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            if (user.getPassword().length() < 4) return false;
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

    @Override
    public String forgot(ForgotUser forgotUser) {
        String newPassword = RandomString.RandomStr(8);
        String query = "UPDATE users SET password=? WHERE id=? AND secretQuestion=? AND secretAnswer=? AND email=?";

        Object[] params = new Object[]{Hashing.sha256().hashUnencodedChars(newPassword).toString(), forgotUser.getId(), forgotUser.getSecretQuestion(), forgotUser.getSecretAnswer(), forgotUser.getEmail()};
        int[] types = new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

        Integer affectedRows = jdbcTemplate.update(query, params, types);
        if (affectedRows == 0)
            return null;
        else return newPassword;
    }
}


