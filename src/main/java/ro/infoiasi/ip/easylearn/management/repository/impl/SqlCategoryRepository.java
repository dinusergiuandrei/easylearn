package ro.infoiasi.ip.easylearn.management.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.management.model.Category;
import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.repository.api.CategoryRepository;

import java.util.List;

@Repository
public class SqlCategoryRepository implements CategoryRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long getLastID()
    {
        if(jdbcTemplate == null)
            System.out.println("NULL TEMPLATE");

        Long id = jdbcTemplate.queryForObject("SELECT MAX(categoryID) FROM categorii", Long.class);

        return id;
    }

    @Override
    public Long save(Category submission) {
        return null;
    }

    @Override
    public Category findById(Long id) {
        List<Category> categories= jdbcTemplate.query("SELECT * FROM categorii where categoryID="+id+"", new BeanPropertyRowMapper<>(Category.class));
        if(categories.size()>=1){
            return categories.get(0);}
        else return null;
    }

    @Override
    public List<Category> findAll() {
        try
        {
            List<Category> categories= jdbcTemplate.query("SELECT * FROM categorii", new BeanPropertyRowMapper<>(Category.class));
            return categories;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
