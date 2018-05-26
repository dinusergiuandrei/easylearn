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


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class CategoryControllerTest {

    @Test
    public void testNothing() {

    }
}