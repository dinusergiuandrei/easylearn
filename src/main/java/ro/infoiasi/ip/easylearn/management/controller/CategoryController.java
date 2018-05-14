package ro.infoiasi.ip.easylearn.management.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import ro.infoiasi.ip.easylearn.management.model.Category;
import ro.infoiasi.ip.easylearn.management.repository.api.CategoryRepository;

import java.util.List;

@RestController
@Api(value = "category", description = "text1")
public class CategoryController {
    CategoryRepository c;

    public CategoryController(CategoryRepository category) {
        this.c = category;
    }

    @RequestMapping(path = "/category/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Category categories(@PathVariable Long id) {
        return c.findById(id);
    }

    @RequestMapping(path = "/category", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> allCategories(){
        List<Category> C=c.findAll();
        return C;
    }

}
