package ro.infoiasi.ip.easylearn.user.repository.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.infoiasi.ip.easylearn.user.model.User;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
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


    @Override
    public Integer getTotalUsers(){
        Integer total = 0;

       /* if(jdbcTemplate == null){
            return -10000;
        }*/

        try{
            String query = "SELECT COUNT(*) FROM users";
            total = jdbcTemplate.queryForObject(query, Integer.class);
        }catch (NullPointerException e){
            return -1;
       }

        return total;
    }

    @Override
    public boolean register(User user){
        if(isAvailableEmail(user.getEmail())){
            String query = "INSERT INTO users (nume, prenume, email, userID, parola) VALUES (?,?,?,?,?)";
            Object[] params = new Object[] {user.getNume() , user.getPrenume(), user.getEmail(), user.getUserID(), user.getParola() };
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

            int row = jdbcTemplate.update(query, params, types);

            return true;
        }
        return false;
    }

    public boolean isAvailableEmail(String email){
        String query = "SELECT COUNT(*) FROM users WHERE email LIKE '" + email + "'";
        int total = jdbcTemplate.queryForObject(query, Integer.class);

       if(total == 0){
           return true;
       }else{
           return false;
       }
    }
    
    @Override
    public String getLastId()
    {
    	String id = jdbcTemplate.queryForObject("SELECT MAX(userID) + 1 FROM users", String.class);

        return id;
    }
    
    @Override
    public boolean login(String email, String password)
    {
    	String query = "SELECT COUNT(*) FROM users WHERE email='" + email + "' AND parola='" + password + "'";
        int total = jdbcTemplate.queryForObject(query, Integer.class);
        
    	return total==1;
    }
}


