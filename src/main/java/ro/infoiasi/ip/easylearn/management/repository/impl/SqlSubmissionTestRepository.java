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
        List<SubmissionTest> test= jdbcTemplate.query("SELECT * FROM submission_tests where id="+id+"", new BeanPropertyRowMapper<>(SubmissionTest.class));
        if(test.size()>=1){
            return test.get(0);}
        else return null;
    }

    @Override
    public List<SubmissionTest> findAll() {
        try
        {
            List<SubmissionTest> teste= jdbcTemplate.query("SELECT * FROM submission_tests", new BeanPropertyRowMapper<>(SubmissionTest.class));
            return teste;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<SubmissionTest> findAllForSubmission(int submissionId)  {
        try
        {
            List<SubmissionTest> tests= jdbcTemplate.query("SELECT * FROM submission_tests WHERE submissionId=" + Integer.toString(submissionId), new BeanPropertyRowMapper<>(SubmissionTest.class));
            return tests;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<SubmissionTest> findTestsForSubmissionByStatus(int submissionId, String status)
    {
        try
        {
            List<SubmissionTest> tests= jdbcTemplate.query("SELECT * FROM submission_tests WHERE submissionId=" + Integer.toString(submissionId) + " and status='" + status + "'", new BeanPropertyRowMapper<>(SubmissionTest.class));
            return tests;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
