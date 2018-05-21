package ro.infoiasi.ip.easylearn.management.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.management.model.SubmissionTest;
import ro.infoiasi.ip.easylearn.management.repository.api.SubmissionTestRepository;

import java.util.List;
 @Repository
public class SqlSubmissionTestRepository implements SubmissionTestRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long save(SubmissionTest submission) {
        return null;
    }

    @Override
    public SubmissionTest findById(Long id) {
        List<SubmissionTest> test= jdbcTemplate.query("SELECT * FROM submissiontests where id="+id+"", new BeanPropertyRowMapper<>(SubmissionTest.class));
        if(test.size()>=1){
            return test.get(0);}
        else return null;
    }

    @Override
    public List<SubmissionTest> findAll() {
        try
        {
            List<SubmissionTest> teste= jdbcTemplate.query("SELECT * FROM submissiontests", new BeanPropertyRowMapper<>(SubmissionTest.class));
            return teste;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<SubmissionTest> findAllForProblem(int problemID) {
        try
        {
            List<SubmissionTest> teste= jdbcTemplate.query("SELECT * FROM submissiontests WHERE problemID=" + Integer.toString(problemID), new BeanPropertyRowMapper<>(SubmissionTest.class));
            return teste;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<SubmissionTest> findAllForSubmission(int submissionID) {
        try
        {
            List<SubmissionTest> teste= jdbcTemplate.query("SELECT * FROM submissiontests WHERE submissionID=" + Integer.toString(submissionID), new BeanPropertyRowMapper<>(SubmissionTest.class));
            return teste;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
