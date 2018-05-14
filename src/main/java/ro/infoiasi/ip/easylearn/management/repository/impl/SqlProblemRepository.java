package ro.infoiasi.ip.easylearn.management.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;

@Repository
public class SqlProblemRepository implements ProblemRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Problem submission) {
        return null;
    }

    @Override
    public Problem findById(Long id) {
    	try
    	{
	        List<Problem> problems= jdbcTemplate.query("SELECT * FROM problems where problemId='"+id+"'", new BeanPropertyRowMapper<>(Problem.class));
	        if(problems.size()>=1)
	            return problems.get(0);
	    }
    	catch(Exception e)
    	{
    	}
		return null;
    }

    @Override
    public List<Problem> findAll() {
        return null;
    }
}
