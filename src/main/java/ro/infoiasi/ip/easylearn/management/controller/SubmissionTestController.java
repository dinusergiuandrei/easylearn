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
    SubmissionTestRepository t;

    public SubmissionTestController(SubmissionTestRepository t){this.t=t;}

    @RequestMapping(path = "/submissionTest/id={id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns the submission test with the specified id")
    public SubmissionTest tests (@PathVariable Long id)
    {
        return t.findById(id);
    }

    @RequestMapping(path = "/submissionTest/all", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns all submission tests in the database")
    public List<SubmissionTest> allTests (){
        List<SubmissionTest> listOfTest=t.findAll();
        return listOfTest;
    }
    
    @RequestMapping(path = "/submissionTest/submissionID={submissionID}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns submission tests for the specified submission")
    public List<SubmissionTest> allTestsForSubmission (@PathVariable int submissionID){
        List<SubmissionTest> listOfTest = t.findAllForSubmission(submissionID);
        return listOfTest;
    }
    
    @RequestMapping(path = "/submissionTest/submissionID={submissionID}/status={status}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Returns passed/failed submission tests for the specified submission")
    public List<SubmissionTest> passedTests (@PathVariable("submissionID") int submissionID, @PathVariable("status") String status){
        List<SubmissionTest> listOfTest = t.findTestsForSubmissionByStatus(submissionID, status);
        return listOfTest;
    }
}
