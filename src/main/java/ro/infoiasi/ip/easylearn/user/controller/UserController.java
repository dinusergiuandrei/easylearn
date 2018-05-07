package ro.infoiasi.ip.easylearn.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionRequest;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionResponse;
import ro.infoiasi.ip.easylearn.submission.service.SubmissionService;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.user.model.User;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;

import java.util.List;

// dependency injection -- submissionService
// Source: https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/


@RestController
@Api(value = "users", description = "text1")
public class UserController {

    UserRepository u;

    public UserController( UserRepository u)
    {
        this.u=u;
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User submissions(@PathVariable Long id) {
        return u.findById(id);
    }

}
