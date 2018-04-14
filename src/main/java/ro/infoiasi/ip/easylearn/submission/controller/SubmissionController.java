package ro.infoiasi.ip.easylearn.submission.controller;

import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionResponse;
import ro.infoiasi.ip.easylearn.submission.service.SubmissionService;
import ro.infoiasi.ip.easylearn.submission.model.Submission;

import java.util.List;

// dependency injection -- submissionService


@RestController
public class SubmissionController {
    private SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @RequestMapping(path = "/submissions", method = RequestMethod.GET)
    @ResponseBody
    public List<Submission> submissions() {
        return submissionService.findAll();
    }

    @RequestMapping(path = "/submissions/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Submission submissions(@PathVariable Long id) {
        return submissionService.findById(id);
    }

    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public SubmissionResponse submit(@RequestBody Submission submission) {
        return submissionService.submit(submission);
    }

}
