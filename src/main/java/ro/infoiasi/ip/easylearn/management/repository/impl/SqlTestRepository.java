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
    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper <ProblemTest> rowMapper = new BeanPropertyRowMapper <>(ProblemTest.class);

    @Override
    public Long save(ProblemTest submission) {
        return null;
    }

    @Override
    public ProblemTest findById(Long id) {
        String findById = "SELECT * FROM tests where id=?";
        List <ProblemTest> tests = jdbcTemplate.query(findById, rowMapper, id);

        return tests.isEmpty() ? null : tests.get(0);
    }

    @Override
    public List <ProblemTest> findAll() {
        String findAll = "SELECT * FROM tests";
        return jdbcTemplate.query(findAll, rowMapper);
    }

    @Override
    public List <ProblemTest> findByProblemId(Long problemId) {
        String findByProblemId = "SELECT * FROM tests WHERE problemId=?";
        return jdbcTemplate.query(findByProblemId, rowMapper, problemId);
    }
}
