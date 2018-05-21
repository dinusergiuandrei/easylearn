package ro.infoiasi.ip.easylearn.management.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.management.model.ProblemTest;
import ro.infoiasi.ip.easylearn.management.repository.api.TestRepository;

import java.util.List;

@RestController
@Api (value="test",description ="Operations pertaining to the manipulations of tests")
public class TestController {
    TestRepository t;

    public TestController(TestRepository t){this.t=t;}

    @RequestMapping(path = "/test/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns the problem test with the specified id")
    public ProblemTest tests (@PathVariable Long id)
    {
        return t.findById(id);
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns all tests in the database")
    public List<ProblemTest> allTests (){
        List<ProblemTest> listOfTest=t.findAll();
        return listOfTest;
    }
    
    @RequestMapping(path = "/problem?id={problemID}/tests", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns tests for the specified problem")
    public List<ProblemTest> allTestsForProblem (@PathVariable int problemID){
        List<ProblemTest> listOfTest = t.findAllForProblem(problemID);
        return listOfTest;
    }
}
