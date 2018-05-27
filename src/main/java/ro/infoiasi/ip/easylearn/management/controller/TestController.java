package ro.infoiasi.ip.easylearn.management.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.management.model.ProblemTest;
import ro.infoiasi.ip.easylearn.management.repository.api.TestRepository;
import ro.infoiasi.ip.easylearn.user.repository.api.SessionRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(description = "Operations pertaining to the manipulations of tests")
public class TestController {
    private TestRepository testRepository;
    private SessionRepository sessionRepository;

    public TestController(TestRepository testRepository, SessionRepository sessionRepository) {
        this.testRepository = testRepository;
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping(path = "/test/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View the problem test with the specified id")
    public ProblemTest findById(@PathVariable Long id, HttpServletRequest request) {
        sessionRepository.MustBeLoggedIn(request);
        return testRepository.findById(id);
    }

    @RequestMapping(path = "/tests", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View all tests")
    public List<ProblemTest> allTests(HttpServletRequest request) {
        sessionRepository.MustBeLoggedIn(request);
        return testRepository.findAll();
    }

    @RequestMapping(path = "/problems/{id}/tests", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns tests for the specified problem")
    public List<ProblemTest> findProblemTests(@PathVariable Long id, HttpServletRequest request) {
        sessionRepository.MustBeLoggedIn(request);
        return testRepository.findByProblemId(id);
    }
}
