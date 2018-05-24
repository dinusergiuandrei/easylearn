package ro.infoiasi.ip.easylearn.management.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.management.model.SubmissionTest;
import ro.infoiasi.ip.easylearn.management.repository.api.SubmissionTestRepository;

import java.util.List;

@RestController
@Api (value="test",description ="Operations pertaining to the manipulations of submission tests")
public class SubmissionTestController {
    SubmissionTestRepository testRepository;

    public SubmissionTestController(SubmissionTestRepository testRepository){this.testRepository =testRepository;}

    @RequestMapping(path = "/submissionTest/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View submission test with the specified id")
    public SubmissionTest tests (@PathVariable Long id)
    {
        return testRepository.findById(id);
    }

    @RequestMapping(path = "/submissionTests", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View all submission tests")
    public List<SubmissionTest> allTests (){
        List<SubmissionTest> listOfTest= testRepository.findAll();
        return listOfTest;
    }
    
    @RequestMapping(path = "/submissionTest/{submissionID}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View submission tests for the specified submission")
    public List<SubmissionTest> allTestsForSubmission (@PathVariable int submissionID){
        List<SubmissionTest> listOfTest = testRepository.findAllForSubmission(submissionID);
        return listOfTest;
    }
    
    @RequestMapping(path = "/submissionTests", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns passed/failed submission tests for the specified submission")
    public List<SubmissionTest> passedTests (@PathVariable("submissionID") int submissionID, @PathVariable("status") String status){
        List<SubmissionTest> listOfTest = testRepository.findTestsForSubmissionByStatus(submissionID, status);
        return listOfTest;
    }
}
