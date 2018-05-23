package ro.infoiasi.ip.easylearn.management.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.management.model.Category;
import ro.infoiasi.ip.easylearn.management.repository.api.CategoryRepository;
import ro.infoiasi.ip.easylearn.management.repository.utils.CategoryMapper;

import java.util.List;

@Repository
public class SqlCategoryRepository implements CategoryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    // ok, do not change
    @Override
    public Category findById(Long id) {
        String query = "SELECT * FROM categories where id=" + id + "";
        List <Category> categories = jdbcTemplate.query(query, new CategoryMapper());

        if (categories.size() > 0) {
            return categories.get(0);
        } else {
            return null;
        }
    }

    // ok, do not change
    @Override
    public List <Category> findAll() {
        String query = "SELECT * FROM categories";
        List <Category> categories = jdbcTemplate.query(query, new CategoryMapper());

        return categories;
    }
}
