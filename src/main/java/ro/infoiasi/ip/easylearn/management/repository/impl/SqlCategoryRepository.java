package ro.infoiasi.ip.easylearn.management.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.management.model.Category;
import ro.infoiasi.ip.easylearn.management.repository.api.CategoryRepository;
import ro.infoiasi.ip.easylearn.management.repository.utils.CategoryMapper;

import java.util.List;

// this is now correctly implemented => do not change
@Repository
public class SqlCategoryRepository implements CategoryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Category findById(Long id) {
        String query = "SELECT * FROM categories where id=?";
        List <Category> categories = jdbcTemplate.query(query, new CategoryMapper(), id);

        if (categories.size() > 0) {
            return categories.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List <Category> findAll() {
        String query = "SELECT * FROM categories";
        List <Category> categories = jdbcTemplate.query(query, new CategoryMapper());

        return categories;
    }
}
