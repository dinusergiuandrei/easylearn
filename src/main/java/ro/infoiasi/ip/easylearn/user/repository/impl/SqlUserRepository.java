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


@Repository
public class SqlUserRepository implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // works perfect => do not changedo not change
    @Override
    public boolean update(User user) {
        try {
            String query = "UPDATE users set name=?, firstName=?, password=?, email=? where id=?";
            Object[] params = new Object[]{user.getName(), user.getFirstName(), user.getPassword(), user.getEmail(), user.getId()};
            int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
            jdbcTemplate.update(query, params, types);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // works perfect => do not change
    @Override
    public User findById(Long id) {
        String selectUserById = "SELECT * FROM users where id=?";
        List <User> users = jdbcTemplate.query(selectUserById, new UserMapper(), id);

        return users.isEmpty() ? null : users.get(0);
    }

    // works perfect
    @Override
    public Long findByEmail(String email) {
        String query = "SELECT * FROM users where email=?";
        List <User> users = jdbcTemplate.query(query, new UserMapper(), email);

        return users.isEmpty() ? null : users.get(0).getId();
    }

    @Override
    public List <User> findAll() {
        // works perfect => do not change
        String selectAllUsers = "SELECT * FROM users";
        return jdbcTemplate.query(selectAllUsers, new UserMapper());
    }

    @Override
    public Long register(User user) {
        if (isAvailableEmail(user.getEmail())) {
            String query = "INSERT INTO users (name, firstName, email, password, secretAnswer, secretPassword) VALUES (?,?,?,?,?,?)";
            Object[] params = new Object[]{user.getName(), user.getFirstName(), user.getEmail(), Hashing.sha256().hashUnencodedChars(user.getPassword()), user.getSecretAnswer(), user.getSecretPassword()};
            int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

            jdbcTemplate.update(query, params, types);

            return findByEmail(user.getEmail());
        }
        else{
            return null;
        }
    }

    private boolean isAvailableEmail(String email) {
        String query = "SELECT * FROM users WHERE email LIKE ?";
        List <User> users = jdbcTemplate.query(query, new UserMapper(), email);

        return users.isEmpty();
    }

    @Override
    public boolean login(String email, String password) {
        String query = "SELECT * FROM users WHERE email=? AND password=?";
        List<User> users = jdbcTemplate.query(query, new UserMapper(), email, Hashing.sha256().hashUnencodedChars(password));

        return users.isEmpty();
    }


    // TODO: THIS IS TRICKY => WATCH OUT FOR FOREIGN KEY CONSTRAINTS
    // TODO: trigger to delete from all table where is referenced
    @Override
    public boolean delete(Long id) {
        try {
            String query = "DELETE FROM users where id=?";
            jdbcTemplate.update(query, id);

            return true;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}


