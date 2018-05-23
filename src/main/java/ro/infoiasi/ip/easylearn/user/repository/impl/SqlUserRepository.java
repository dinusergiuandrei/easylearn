package ro.infoiasi.ip.easylearn.user.repository.impl;

import ro.infoiasi.ip.easylearn.user.model.User;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.user.repository.utils.UserMapper;

import java.sql.Types;
import java.util.*;


@Repository
public class SqlUserRepository implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean updateData(User user) {
        try {
            String query = "UPDATE users set name=?, firstName=?, password=? where email='" + user.getEmail() + "' OR id='" + user.getId() + "'";
            Object[] params = new Object[]{user.getName(), user.getFirstName(), user.getPassword()};
            int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
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
        List <User> users = jdbcTemplate.query("SELECT * FROM users where id='" + id + "'", new UserMapper());
        if (users.size() >= 1)
            return users.get(0);
        else return null;
    }

    // works perfect => do not change
    @Override
    public List <User> findAll() {
        String query = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(query, new UserMapper());

        return users;
    }

    // works perfect => do not change
    @Override
    public Integer getTotalUsers() {
        Integer total = 0;

        try {
            String query = "SELECT COUNT(*) FROM users";
            total = jdbcTemplate.queryForObject(query, Integer.class);
        } catch (NullPointerException e) {
            return -1;
        }

        return total;
    }

    // this works fine => don't change (maybe after you apply a hash function on secret fields"
    @Override
    public boolean register(User user) {
        if (isAvailableEmail(user.getEmail())) {
            String query = "INSERT INTO users (name, firstName, email, password, secretAnswer, secretPassword) VALUES (?,?,?,?,?,?)";
            Object[] params = new Object[]{user.getName(), user.getFirstName(), user.getEmail(), user.getPassword(), user.getSecretAnswer(), user.getSecretPassword()};
            int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

            int row = jdbcTemplate.update(query, params, types);

            return true;
        }
        return false;
    }

    public boolean isAvailableEmail(String email) {
        String query = "SELECT COUNT(*) FROM users WHERE email LIKE '" + email + "'";
        int total = jdbcTemplate.queryForObject(query, Integer.class);

        if (total == 0) {
            return true;
        } else {
            return false;
        }
    }

    // know this works fine, dont change
    @Override
    public Long getLastId() {
        Long id = jdbcTemplate.queryForObject("SELECT MAX(id) FROM users", Long.class);

        return id;
    }


    // I don't think this should be implemented because the session is
    // responsible with this
    @Override
    public boolean login(String email, String password) {
        String query = "SELECT COUNT(*) FROM users WHERE email='" + email + "' AND password='" + password + "'";
        int total = jdbcTemplate.queryForObject(query, Integer.class);

        return total == 1;
    }

    // works perfect => do not change
    @Override
    public int getScore(Long userId) {
        String query = "select score from users where id='" + userId + "'";
        List <Integer> scores = jdbcTemplate.queryForList(query, Integer.class);

        return scores.get(0);
    }


    // TODO: THIS IS TRICKY => WATCH OUT FOR FOREIGN KEY CONSTRAINTS
    @Override
    public boolean delete(Long id) {
        try {
            String query = "DELETE FROM users where id='" + id + "'";
            jdbcTemplate.update(query);

            return true;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}


