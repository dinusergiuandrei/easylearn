package ro.infoiasi.ip.easylearn.management.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ro.infoiasi.ip.easylearn.management.controller.CategoryController;
import ro.infoiasi.ip.easylearn.management.repository.api.CategoryRepository;

import javax.transaction.Transactional;
import java.util.Random;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)

public class CategoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryController categoryController = new CategoryController(categoryRepository);

    @Test
    public void setCategoryId() {
        Category mycategory = new Category();
        Random rand = new Random();

        int id = rand.nextInt(150) + 1;

        mycategory.setCategoryId(id);

        final long getId = mycategory.getCategoryId();

        Assert.assertEquals("Fields don't match",id, getId);
    }

    @Test
    public void setNume() {
        Category mycategory = new Category();
        String nume = "mediu";

        mycategory.setNume(nume);

        final String getnume = mycategory.getNume();

        Assert.assertEquals("Fields don't match", nume, getnume);

    }

    @Test
    public void getCategoryId(){
        Category myCategory = new Category();
        Random rand = new Random();

        int id = rand.nextInt(150) + 1;

        myCategory.setCategoryId(id);

        final long getId = myCategory.getCategoryId();

        Assert.assertEquals("Fields don't match", id, getId);
    }

    @Test
    public void getNume(){
        Category mycategory = new Category();

        mycategory.setNume("mediu");

        final String getnume = mycategory.getNume();

        Assert.assertEquals("Fields don't match", "mediu", getnume);
    }
}