package ro.infoiasi.ip.easylearn.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.user.exception.LoginFailedException;
import ro.infoiasi.ip.easylearn.user.exception.UserDataCouldNotBeDeletedException;
import ro.infoiasi.ip.easylearn.user.exception.UserDataCouldNotBeUpdatedException;
import ro.infoiasi.ip.easylearn.user.exception.UserNotFoundException;
import ro.infoiasi.ip.easylearn.user.model.User;
import ro.infoiasi.ip.easylearn.user.model.UserResponse;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@Api(description = "Operations pertaining to the manipulations of users")
public class UserController {

    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/users", method = GET)
    @ResponseBody
    @ApiOperation(value = "View the information about all the users entered in the system")
    public List<User> users() {
        return userRepository.findAll();
    }

    @RequestMapping(path = "/users", method = POST)
    @ResponseBody
    @ApiOperation(value = "Registers the user")
    public UserResponse register(@RequestBody User user) {
        userRepository.register(user);
        Long id = userRepository.findByEmail(user.getEmail());

        return new UserResponse(id);
    }

    @RequestMapping(path = "/users/{id}", method = GET)
    @ResponseBody
    @ApiOperation(value = "View the user's information")
    public User user(@PathVariable Long id) throws UserNotFoundException {
        User user = userRepository.findById(id);

        if(user == null){
            throw new UserNotFoundException();
        } else{
            return user;
        }
    }

    @RequestMapping(path = "/users/{id}", method = PUT)
    @ResponseBody
    @ApiOperation(value = "Update user's data")
    public void update(@RequestBody User user) {
        boolean updated = userRepository.update(user);
        if (!updated) {
            throw new UserDataCouldNotBeUpdatedException();
        }
    }

    @RequestMapping(path = "/user/{id}", method = DELETE)
    @ResponseBody
    @ApiOperation(value = "Delete a user")
    public void delete(@RequestParam Long id) {
        boolean deleted = userRepository.delete(id);
        if(!deleted){
            throw new UserDataCouldNotBeDeletedException();
        }
    }

    @RequestMapping(path = "/login", method = POST)
    @ResponseBody
    @ApiOperation(value = "Logins a user")
    public void login(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
        boolean loggedIn =  userRepository.login(email, password);

        if(loggedIn){
            //TODO: create session and obtain id
            // sessionRepository etc
            response.addCookie(new Cookie("sid", "session-id"));
        } else{
            throw new LoginFailedException();
        }
    }

}
