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
import ro.infoiasi.ip.easylearn.user.repository.impl.SqlUserRepository;

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

    @RequestMapping(path = "/users/id={id}", method = RequestMethod.GET)
    @ResponseBody
    public User submissions(@PathVariable Long id) {
        return u.findById(id);
    }

    @RequestMapping(path = "/users/testNumberOfUsers", method = RequestMethod.GET)
    @ResponseBody
    public String getTotalNumberOfUsers(){return u.getTotalUsers()+" users";}

    @RequestMapping(path = "/users/register", method = RequestMethod.GET)
    @ResponseBody
    public boolean registerUser(/*User user*/){
        User user = new User();
        user.setParola("sa5ke");
        user.setNume("Ionescu");
        user.setPrenume("Vlad");
        user.setEmail("iones_vl@mymail.com");
        //user.setEmail("dan@man.com");
        user.setUserID("4");
        return u.register(user);
    }

}
