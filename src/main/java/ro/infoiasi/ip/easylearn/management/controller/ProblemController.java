package ro.infoiasi.ip.easylearn.management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ro.infoiasi.ip.easylearn.management.exception.ProblemNotFoundException;
import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.model.ProblemResponse;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@Api(description = "Operations pertaining to the manipulations of problems")
public class ProblemController {

    ProblemRepository problemRepository;


    public ProblemController(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @RequestMapping(path = "/problems", method = GET)
    @ResponseBody
    @ApiOperation(value = "View all problems")
    public List <Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    @RequestMapping(path = "/problems/{id}", method = GET)
    @ResponseBody
    @ApiOperation(value = "Returns the problem with the specified id")
    public Problem getProblemById(@PathVariable Long id) {
        Problem problem = problemRepository.findById(id);

        if (problem == null) {
    	    throw new ProblemNotFoundException();
        }

        return problem;
    }

    @RequestMapping(path = "/problems/category/{id}", method = GET)
    @ResponseBody
    @ApiOperation(value = "Returns all the problems in the provided category")
    public List <Problem> getProblemsByCategory(@PathVariable Long categoryId) {
        return problemRepository.findByCategory(categoryId);
    }

    @RequestMapping(path = "/problems/author/{id}", method = GET)
    @ResponseBody
    @ApiOperation(value = "Returns all the problems posted by the author identified by the provided userID")
    public List <Problem> getProblemsByAuthor(@PathVariable Long authorId) {
        return problemRepository.findByAuthor(authorId);
    }

    @RequestMapping(path = "/problems/solved/{userId}", method = GET)
    @ResponseBody
    @ApiOperation(value = "Returns all problems solved by the user identified by the provided userID")
    public List <Problem> getSolvedProblems(@PathVariable Long userId) {
        return problemRepository.findSolved(userId);
    }

    @RequestMapping(path = "/problems/attempted/{userId}", method = GET)
    @ResponseBody
    @ApiOperation(value = "Returns all problems attempted by the user identified by the provided userID (at least one submission)")
    public List <Problem> getAttemptedProblems(@PathVariable Long userId) {
        return problemRepository.findAttempted(userId);
    }

    @RequestMapping(path = "/problems", method = POST)
    @ResponseBody
    @ApiOperation(value = "Adds a new problem")
    public ProblemResponse addProblem(@RequestBody Problem problem) {
        Long id = problemRepository.save(problem);
        return new ProblemResponse(id);
    }

    @RequestMapping(path = "/problems/populate", method = POST)
    @ResponseBody
    @ApiOperation(value = "Test method for inserting a dummy problem")
    public Long populateTable() {
        //problema de test
        //datele vor fi preluate prin json
        Problem problem = new Problem(
                null,
                1L,
                1L,
                "Camioane", "Problema Camioane",
                "O firma are doua tipuri de camioane: camioane de tipul 1, care pot transporta cate t1 tone de marfa pe zi, si camioane de tipul 2, care pot transporta cate t2 tone de marfa pe zi. \n Stiind ca firma are n camioane de tipul 1 si m camioane de tipul 2, cate tone de marfa pot transporta camioanele firmei in z zile.",
                "Programul citeste de la tastatura numerele naturale t1 t2 n m z.",
                "Programul va afisa pe ecran rezultatul.",
                "2 < t1, t2 < 100\n" +
                        "2 < n, m < 100\n" +
                        "2 < z < 30",
                1,
                "tastatura",
                "3 5 4 2 5",
                "110",
                "",
                "",
                1L,
                1L);

        return problemRepository.save(problem);
    }
}
