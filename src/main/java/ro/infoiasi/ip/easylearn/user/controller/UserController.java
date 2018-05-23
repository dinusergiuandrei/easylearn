package ro.infoiasi.ip.easylearn.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.user.model.User;
import ro.infoiasi.ip.easylearn.user.model.UserResponse;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;

// dependency injection -- submissionService
// Source: https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/


@RestController
@Api(value = "users", description = "Operations pertaining to the manipulations of users")
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/users/id={id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View the user's information")
    public User submissions(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @RequestMapping(path = "/users/testNumberOfUsers", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "View the number of users")
    public String getTotalNumberOfUsers() {
        return userRepository.getLastId() + " users";
    }

    @RequestMapping(path = "/users/register", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Register the user")
    public String registerUser(@RequestBody User jsonUser) {
        // this works perfect
        // if you have time maybe make the same changes I suggested in ProblemController : /problems/add
        userRepository.register(jsonUser);
        Long id = userRepository.getLastId();
        UserResponse userResponse = new UserResponse(id);

        String response = userResponse.getMessage() + "\n" + userResponse.getUri();
        return response;
    }

    @RequestMapping(path = "/users/registerTest", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Register the user, returns 'true' if successfull, else if not")
    public boolean registerUser() {
        User user = new User();
        user.setPassword("sa5ke");
        user.setName("Ionescu");
        user.setFirstName("Vlad");
        user.setEmail("iones_vls@mymail.com");
        user.setId(userRepository.getLastId());
        return userRepository.register(user);
    }

    @RequestMapping(path = "/users/login/email={email};password={password}", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Login using the provided credentials, returns 'true' if successfull, else if not")
    public boolean login(@PathVariable String email, @PathVariable String password) {
        return userRepository.login(email, password);
    }

    @RequestMapping(path = "/users/loginTest", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Login using the provided credentials, returns 'true' if successfull, else if not")
    public boolean login() {
        return userRepository.login("dan@man.com", "danmanx");
    }
    
    @RequestMapping(path = "/users/update/email={email},first_name={first_name},last_name={last_name},password={password}", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "Update user's data, returns 'true' if successfull, else if not")
    public boolean updateData(@PathVariable String email, @PathVariable String firstName, @PathVariable String name, @PathVariable String password) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setName(name);
        user.setPassword(password);
    	return userRepository.updateData(user);
    }
    
    @RequestMapping(path = "/users/score/userID={userID}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Get the user's total score")
    public int getScore(@PathVariable Long userId)
    {
    	return userRepository.getScore(userId);
    }
    
    @RequestMapping(path = "/users/delete/userID={userID}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "Delete user with this id")
    public boolean deleteUser(@PathVariable Long id)
    {
    	return userRepository.delete(id);
    }
}
