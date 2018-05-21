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
    public Long save(Problem P) {
        return null;
    }

    @Override
    public Problem findById(Long id) {
    	try
    	{
	        List<Problem> problems= jdbcTemplate.query("SELECT * FROM probleme where problemId="+id+"", new BeanPropertyRowMapper<>(Problem.class));
	        if(problems.size()>=1)
	            return problems.get(0);
			return null;
	    }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
    
    @Override
    public List<Problem> findAll() {
    	try
    	{
	        List<Problem> problems= jdbcTemplate.query("SELECT * FROM probleme", new BeanPropertyRowMapper<>(Problem.class));
	        return problems;
	    }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
    
    @Override
    public boolean add(Problem P)
    {
    	try
    	{
        	if(jdbcTemplate == null)
        		System.out.println("NULL TEMPLATE");
        	
	        jdbcTemplate.update("INSERT INTO probleme VALUES (" + P.toValuesString() + ")");
			return true;
	    }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return false;
    	}
    }
    
    @Override
    public Long getLastID()
    {
    	if(jdbcTemplate == null)
    		System.out.println("NULL TEMPLATE");
    	
    	Long id = jdbcTemplate.queryForObject("SELECT MAX(problemID) FROM probleme", Long.class);
    	
    	return id;
    }

    @Override
    public List<Problem> findByCategory(long catid)
    {
    	try
    	{
	        List<Problem> problems= jdbcTemplate.query("SELECT * FROM probleme where categorie="+catid, new BeanPropertyRowMapper<>(Problem.class));
	        return problems;
	    }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
    
    @Override
    public List<Problem> findByAuthor(String authorid)
    {
    	try
    	{
	        List<Problem> problems= jdbcTemplate.query("SELECT * FROM probleme where authorID=" + authorid, new BeanPropertyRowMapper<>(Problem.class));
	        return problems;
	    }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
    
    public List<Problem> findSolved(int userID)
    {
    	try
    	{
	        List<Problem> problems= jdbcTemplate.query("SELECT * FROM probleme p where (select count(*) from submissions s where s.problemID = p.problemID and s.score = 100 and s.userID = " + Integer.toString(userID) + ") > 0", new BeanPropertyRowMapper<>(Problem.class));
	        return problems;
	    }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
    

    public List<Problem> findAttempted(int userID)
    {
    	try
    	{
    		List<Problem> problems= jdbcTemplate.query("SELECT * FROM probleme p where (select count(*) from submissions s where s.problemID = p.problemID and s.userID = " + Integer.toString(userID) + ") > 0", new BeanPropertyRowMapper<>(Problem.class));
	        return problems;
	    }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
}
