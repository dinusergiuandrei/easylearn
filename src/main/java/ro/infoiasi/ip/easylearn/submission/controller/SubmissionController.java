package ro.infoiasi.ip.easylearn.submission.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionRequest;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionResponse;
import ro.infoiasi.ip.easylearn.submission.service.SubmissionService;
import ro.infoiasi.ip.easylearn.submission.model.Submission;

import java.util.List;

// dependency injection -- submissionService
// Source: https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/


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
    @ApiOperation(value = "Send a submission for processing. If the request contains special characters, copy Example Value model in: https://jsoneditoronline.org/, select the key icon to repair the JSON, then paste the result in swagger.")
    public SubmissionResponse submit(@RequestBody SubmissionRequest submissionRequest) {
        return submissionService.submit(submissionRequest);
    }

}
