package ro.infoiasi.ip.easylearn.management.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class SqlProblemRepository implements ProblemRepository {

    public  BeanPropertyRowMapper <Problem> rowMapper = new BeanPropertyRowMapper<>(Problem.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Problem problem) {
        String insertQuery = "INSERT INTO problems (userId, categoryId, title, description, requirement, input, output, restrictions, difficulty, dataType, inputExample, outputExample, inputFile, outputFile, memoryLimit, timeLimit) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps =
                        connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, problem.getUserId());
                ps.setLong(2, problem.getCategoryId());
                ps.setString(3, problem.getTitle());
                ps.setString(4, problem.getDescription());
                ps.setString(5, problem.getRequirement());
                ps.setString(6, problem.getInput());
                ps.setString(7, problem.getOutput());
                ps.setString(8, problem.getRestrictions());
                ps.setLong(9, problem.getDifficulty());
                ps.setString(10, problem.getDataType());
                ps.setString(11, problem.getInputExample());
                ps.setString(12, problem.getOutputExample());
                ps.setString(13, problem.getInputFile());
                ps.setString(14, problem.getOutputFile());
                ps.setLong(15, problem.getMemoryLimit());
                ps.setLong(16, problem.getTimeLimit());
                return ps;
            }
        };

        // obtain last generated id
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int generatedKey = jdbcTemplate.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Problem findById(Long id) {
        try {
            String findProblemById = "SELECT * FROM problems where id=?";
            List <Problem> problems = jdbcTemplate.query(findProblemById, rowMapper, id);

            return problems.isEmpty() ? null : problems.get(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List <Problem> findAll() {
        try {
            String findAll = "SELECT * FROM problems";
            return jdbcTemplate.query(findAll, rowMapper);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List <Problem> findByCategory(Long categoryId) {
        try {
            String findByCategory = "SELECT * FROM problems where categoryId=?";
            return jdbcTemplate.query(findByCategory, rowMapper, categoryId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Problem> getRandom() {
        try{
            String query = "SELECT * FROM problems ORDER BY RAND() LIMIT 5";
            return jdbcTemplate.query(query, rowMapper);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List <Problem> findByAuthor(Long authorId) {
        try {
            String findByCategory = "SELECT * FROM problems where authorId=?";
            return jdbcTemplate.query(findByCategory, rowMapper, authorId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // TODO there is no score field in submissions ==> check users
    public List <Problem> findSolved(Long userId) {
        try {
            String findSolved = "SELECT * FROM problems p where (select count(*) from submissions s where s.problemId = p.id and s.score = 100 and s.userId = ?) > 0";
            return jdbcTemplate.query(findSolved, rowMapper);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List <Problem> findAttempted(Long userId) {
        try {
            return  jdbcTemplate.query(
                    "SELECT * FROM problems p where (select count(*) from submissions s where s.problemId = p.id and s.userId = " + userId + ") > 0", new BeanPropertyRowMapper <>(Problem.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
