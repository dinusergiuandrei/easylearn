package ro.infoiasi.ip.easylearn.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.user.model.User;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;

// dependency injection -- submissionService
// Source: https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/


@RestController
@Api(value = "users", description = "Operations pertaining to the manipulations of users")
public class UserController {

    UserRepository u;

    public UserController(UserRepository u) {
        this.u = u;
    }

    @RequestMapping(path = "/users/id={id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View the user's information")
    public User submissions(@PathVariable Long id) {
        return u.findById(id);
    }

    @RequestMapping(path = "/users/testNumberOfUsers", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View the number of users")
    public String getTotalNumberOfUsers() {
        return u.getLastId() + " users";
    }

    @RequestMapping(path = "/users/register", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Register the user, returns 'true' if successfull, else if not")
    public boolean registerUser(@RequestBody User jsonUser) {
        return u.register(jsonUser);
    }

    @RequestMapping(path = "/users/registerTest", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Register the user, returns 'true' if successfull, else if not")
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
    @ApiOperation(value = "Login using the provided credentials, returns 'true' if successfull, else if not")
    public boolean login(@PathVariable String email, @PathVariable String password) {
        return u.login(email, password);
    }

    @RequestMapping(path = "/users/loginTest", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Login using the provided credentials, returns 'true' if successfull, else if not")
    public boolean login() {
        return u.login("dan@man.com", "danmanx");
    }
    
    @RequestMapping(path = "/users/update/email={email},first_name={first_name},last_name={last_name},password={password}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Update user's data, returns 'true' if successfull, else if not")
    public boolean updateData(@PathVariable String email, @PathVariable String first_name, @PathVariable String last_name, @PathVariable String password) {
        User usr = new User();
        usr.setEmail(email); usr.setPrenume(first_name); usr.setNume(last_name); usr.setParola(password); usr.setUserID("-1");
    	return u.updateData(usr);
    }
    
    @RequestMapping(path = "/users/score/userID={userID}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get the user's total score")
    public int getScore(@PathVariable String userID)
    {
    	return u.getScore(userID);
    }
    
    @RequestMapping(path = "/users/delete/userID={userID}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get the user's total score")
    public boolean deleteUser(@PathVariable String userID)
    {
    	return u.delete(userID);
    }
}
