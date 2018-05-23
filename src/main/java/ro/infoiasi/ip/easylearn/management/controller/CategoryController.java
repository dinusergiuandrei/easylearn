package ro.infoiasi.ip.easylearn.management.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ro.infoiasi.ip.easylearn.management.exception.CategoryNotFoundException;
import ro.infoiasi.ip.easylearn.management.model.Category;
import ro.infoiasi.ip.easylearn.management.repository.api.CategoryRepository;

import java.util.List;

@RestController
@Api(description = "Operations pertaining to the manipulations of categories")
public class CategoryController {
    CategoryRepository categoryRepository;
    private Category category;

    public CategoryController(CategoryRepository category) {
        this.categoryRepository = category;
    }

    @RequestMapping(path = "/category/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View information about a particular category")
    public Category categories(@PathVariable Long id) {
        Category category = categoryRepository.findById(id);

        if(category == null){
            throw new CategoryNotFoundException();
        } else{
            return category;
        }

    }

    @RequestMapping(path = "/categories", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View information about all categories")
    public List<Category> categories(){
        return categoryRepository.findAll();
    }

}
