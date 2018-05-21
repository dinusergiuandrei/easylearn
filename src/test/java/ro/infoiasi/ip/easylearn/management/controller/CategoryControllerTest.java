package ro.infoiasi.ip.easylearn.management.controller;

import org.junit.Assert;
import org.junit.Test;
//import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import ro.infoiasi.ip.easylearn.management.repository.api.CategoryRepository;
import ro.infoiasi.ip.easylearn.management.model.Category;

import javax.transaction.Transactional;

import java.sql.Types;
import java.util.Random;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)

public class CategoryControllerTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryController categoryController = new CategoryController(categoryRepository);

    @Test
    //testam pentru un id valid
    public void categoriesValidID() {
        //creez o noua categorie o adaug in baza de date si verific daca e aceeasi returnata de aceasta functie
        Category myCategory = new Category(10, "mediu");
        String query = "INSERT INTO categorii(categoryID, nume) values(?,?);";
        Object[] params = new Object[]{myCategory.getCategoryId(),myCategory.getNume()};
        int[] types = new int[]{Types.INTEGER, Types.VARCHAR};
        int row = jdbcTemplate.update(query, params, types);

        Category categoryById = new Category();
        categoryById = categoryController.categories(myCategory.getCategoryId());
        Assert.assertEquals(myCategory.getCategoryId() + myCategory.getNume(), categoryById.getCategoryId() + categoryById.getNume());
    }


    @Test
    //testam pentru id invalid
    public void categoriesInvalID(){
        Random rand = new Random();

        int offset = rand.nextInt(150) + 1;


        Category categoryByID = new Category();
        long newID = offset + categoryRepository.getLastID();
        categoryByID = categoryController.categories(newID);

        Assert.assertEquals(new Category().getCategoryId() + new Category().getNume(), categoryByID.getCategoryId() + categoryByID.getNume());

    }
}