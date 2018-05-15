package ro.infoiasi.ip.easylearn.user.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.user.model.User;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;

// dependency injection -- submissionService
// Source: https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/


@RestController
@Api(value = "users", description = "text1")
public class UserController {

    UserRepository u;

    public UserController(UserRepository u) {
        this.u = u;
    }

    @RequestMapping(path = "/users/id={id}", method = RequestMethod.GET)
    @ResponseBody
    public User submissions(@PathVariable Long id) {
        return u.findById(id);
    }

    @RequestMapping(path = "/users/testNumberOfUsers", method = RequestMethod.GET)
    @ResponseBody
    public String getTotalNumberOfUsers() {
        return u.getLastId() + " users";
    }

    @RequestMapping(path = "/users/register", method = RequestMethod.GET)
    @ResponseBody
    public boolean registerUser(@RequestBody User jsonUser) {
        return u.register(jsonUser);
    }

    @RequestMapping(path = "/users/registerTest", method = RequestMethod.GET)
    @ResponseBody
    public boolean registerUser() {
        User user = new User();
        user.setParola("sa5ke");
        user.setNume("Ionescu");
        user.setPrenume("Vlad");
        user.setEmail("iones_vls@mymail.com");
        user.setUserID(u.getLastId());
        return u.register(user);
    }

    @RequestMapping(path = "/users/login/email={email};password={password}", method = RequestMethod.GET)
    @ResponseBody
    public boolean login(@PathVariable String email, @PathVariable String password) {
        return u.login(email, password);
    }

    @RequestMapping(path = "/users/loginTest", method = RequestMethod.GET)
    @ResponseBody
    public boolean login() {
        return u.login("dan@man.com", "danmanx");
    }
}
