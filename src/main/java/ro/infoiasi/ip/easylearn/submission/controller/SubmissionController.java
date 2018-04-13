package ro.infoiasi.ip.easylearn.submission.controller;

import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionResponse;
import ro.infoiasi.ip.easylearn.submission.service.SubmissionService;
import ro.infoiasi.ip.easylearn.submission.model.Submission;

import java.util.List;

//curl localhost:8100/submit -d'{"id":"123"}' -H content-type:application/json
// dependency injection -- service


@RestController
public class SubmissionController {
    private SubmissionService service;

    public SubmissionController(SubmissionService service) {

        this.service = service;
    }

    @RequestMapping(path = "/submissions", method = RequestMethod.GET)
    @ResponseBody
    public List<Submission> submissions() {
        return null;
    }

    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public SubmissionResponse submit(@RequestBody Submission submission) {

        return service.submit(submission);
    }
}
