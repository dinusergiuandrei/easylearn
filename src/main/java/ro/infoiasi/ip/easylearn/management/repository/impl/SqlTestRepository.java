package ro.infoiasi.ip.easylearn.management.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.management.model.ProblemTest;
import ro.infoiasi.ip.easylearn.management.repository.api.TestRepository;

import java.util.List;
 @Repository
public class SqlTestRepository implements TestRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long save(ProblemTest submission) {
        return null;
    }

    @Override
    public ProblemTest findById(Long id) {
        List<ProblemTest> test= jdbcTemplate.query("SELECT * FROM teste where testID="+id+"", new BeanPropertyRowMapper<>(ProblemTest.class));
        if(test.size()>=1){
            return test.get(0);}
        else return null;
    }

    @Override
    public List<ProblemTest> findAll() {
        try
        {
            List<ProblemTest> teste= jdbcTemplate.query("SELECT * FROM teste", new BeanPropertyRowMapper<>(ProblemTest.class));
            return teste;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
