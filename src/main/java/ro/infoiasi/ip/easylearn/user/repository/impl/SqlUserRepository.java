package ro.infoiasi.ip.easylearn.user.repository.impl;

import ro.infoiasi.ip.easylearn.user.model.User;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SqlUserRepository implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long save(User submission) {
        return null;
    }

    @Override
    public User findById(Long id) {
        List<User> users= jdbcTemplate.query("SELECT * FROM users where userId='"+id+"'", new BeanPropertyRowMapper<>(User.class));
        if(users.size()>=1)
            return users.get(0);
        else return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}


