package ro.infoiasi.ip.easylearn.submission;

import org.springframework.web.bind.annotation.*;

//curl localhost:8100/submit -d'{"id":"123"}' -H content-type:application/json
// dependency injection -- service


@RestController
public class SubmissionController {
    private SubmissionService service;

    public SubmissionController(SubmissionService service) {

        this.service = service;
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "Success!";
    }

    @RequestMapping(path = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public SubmissionResponse submit(@RequestBody Submission submission) {

        return service.submit(submission);
    }
}
