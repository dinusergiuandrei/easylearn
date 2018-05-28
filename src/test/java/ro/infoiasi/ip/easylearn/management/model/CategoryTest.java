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

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTest {
    @Test
    public void testSetter_setCategoryId() {
        Category mycategory = new Category();
        Random random = new Random();

        Long id = random.nextLong() + 1;

        mycategory.setId(id);

        final Long getId = mycategory.getId();

        Assert.assertEquals("Fields don't match",id, getId);
    }

    @Test
    public void testSetter_setName() {
        Category mycategory = new Category();
        String nume = "mediu";

        mycategory.setName(nume);

        final String getnume = mycategory.getName();

        Assert.assertEquals("Fields don't match", nume, getnume);

    }

    @Test
    public void testGetter_getCategoryId(){
        Category myCategory = new Category();
        Random rand = new Random();

        Long id = rand.nextLong() + 1;

        myCategory.setId(id);

        final Long getId = myCategory.getId();

        Assert.assertEquals("Fields don't match", id, getId);
    }

    @Test
    public void testGetter_getNume(){
        Category mycategory = new Category();

        mycategory.setName("mediu");

        final String getnume = mycategory.getName();

        Assert.assertEquals("Fields don't match", "mediu", getnume);
    }
}