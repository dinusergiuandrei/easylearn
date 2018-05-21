package ro.infoiasi.ip.easylearn.management.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ro.infoiasi.ip.easylearn.management.model.Category;
import ro.infoiasi.ip.easylearn.management.repository.api.CategoryRepository;

import java.util.List;

@RestController
@Api(value = "category", description = "Operations pertaining to the manipulations of categories")
public class CategoryController {
    CategoryRepository c;
    private Category category;

    public CategoryController(CategoryRepository category) {
        this.c = category;
    }

    @RequestMapping(path = "/category/id={id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns the problem category with the provided id")
    public Category categories(@PathVariable Long id) {
        if (c.findById(id) == null){
            category = new Category();
            return  category;
        }
        else
            return c.findById(id);

    }

    @RequestMapping(path = "/category/all", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns the all problem categories")
    public List<Category> allCategories(){
        List<Category> C = c.findAll();
        return C;
    }

}
