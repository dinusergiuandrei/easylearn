package ro.infoiasi.ip.easylearn.submission.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionResponse;
import ro.infoiasi.ip.easylearn.submission.service.SubmissionService;
import ro.infoiasi.ip.easylearn.submission.model.Submission;

import java.util.List;

// dependency injection -- submissionService


@RestController
@Api(value = "submissions", description = "Operations pertaining to the manipulations of submissions")
public class SubmissionController {
    private SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @RequestMapping(path = "/submissions", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View the information about the submissions entered in the system")
    public List<Submission> submissions() {
        return submissionService.findAll();
    }

    @RequestMapping(path = "/submissions/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View the information about a particular submission")
    public Submission submissions(@PathVariable Long id) {
        return submissionService.findById(id);
    }

    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Send a submission for processing")
    public SubmissionResponse submit(@RequestBody Submission submission) {
        return submissionService.submit(submission);
    }

}
