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
    public Long save(Problem problem) {
        return null;
    }

    @Override
    public Problem findById(Long id) {
    	try
    	{
	        List<Problem> problems= jdbcTemplate.query("SELECT * FROM problems where id="+id+"", new BeanPropertyRowMapper<>(Problem.class));
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
	        List<Problem> problems= jdbcTemplate.query("SELECT * FROM problems", new BeanPropertyRowMapper<>(Problem.class));
	        return problems;
	    }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
    
    @Override
    public boolean add(Problem problem)
    {
    	try
    	{
        	if(jdbcTemplate == null)
        		System.out.println("NULL TEMPLATE");
        	
	        jdbcTemplate.update("INSERT INTO problems VALUES (" + problem.toValuesString() + ")");
			return true;
	    }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return false;
    	}
    }
    
    @Override
    public Long getLastId()
    {
    	if(jdbcTemplate == null)
    		System.out.println("NULL TEMPLATE");
    	
    	Long id = jdbcTemplate.queryForObject("SELECT MAX(id) FROM problems", Long.class);
    	
    	return id;
    }

    @Override
    public List<Problem> findByCategory(Long categoryId)
    {
    	try
    	{
	        List<Problem> problems= jdbcTemplate.query("SELECT * FROM problems where categories="+categoryId, new BeanPropertyRowMapper<>(Problem.class));
	        return problems;
	    }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return null;
    	}
    }

	@Override
    public List<Problem> findByAuthor(Long authorId)
    {
    	try
    	{
	        List<Problem> problems= jdbcTemplate.query("SELECT * FROM problems where authorId=" + authorId, new BeanPropertyRowMapper<>(Problem.class));
	        return problems;
	    }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
    
    public List<Problem> findSolved(int userId)
    {
    	try
    	{
	        List<Problem> problems= jdbcTemplate.query("SELECT * FROM problems p where (select count(*) from submissions s where s.problemId = p.id and s.score = 100 and s.userId = " + Integer.toString(userId) + ") > 0", new BeanPropertyRowMapper<>(Problem.class));
	        return problems;
	    }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
    

    public List<Problem> findAttempted(int userId)
    {
    	try
    	{
    		List<Problem> problems= jdbcTemplate.query("SELECT * FROM problems p where (select count(*) from submissions s where s.problemId = p.id and s.userId = " + Integer.toString(userId) + ") > 0", new BeanPropertyRowMapper<>(Problem.class));
	        return problems;
	    }
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
}
