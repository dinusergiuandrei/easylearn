package ro.infoiasi.ip.easylearn.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.user.exception.*;
import ro.infoiasi.ip.easylearn.user.model.LoginRequest;
import ro.infoiasi.ip.easylearn.user.model.User;
import ro.infoiasi.ip.easylearn.user.model.UserResponse;
import ro.infoiasi.ip.easylearn.user.repository.api.SessionRepository;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@Api(description = "Operations pertaining to the manipulations of users")
public class UserController {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;

    public UserController(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping(path = "/users", method = GET)
    @ResponseBody
    @ApiOperation(value = "View the information about all the users")
    public List <User> users() {
        return userRepository.findAll();
    }

    @RequestMapping(path = "/users", method = POST)
    @ResponseBody
    @ApiOperation(value = "Registers the user")
    public UserResponse register(@RequestBody User user) {
        Long id = userRepository.register(user);

        if (id == null) {
            throw new RegistrationFailedException();
        }

        return new UserResponse(id);
    }

    @RequestMapping(path = "/users/{id}", method = GET)
    @ResponseBody
    @ApiOperation(value = "View information about a particular user")
    public User user(@PathVariable Long id) throws UserNotFoundException {
        User user = userRepository.findById(id);

        if (user == null) {
            throw new UserNotFoundException();
        } else {
            return user;
        }
    }

    @RequestMapping(path = "/users/{id}", method = PUT)
    @ApiOperation(value = "Update user's data")
    public void update(@RequestBody User user) {
        boolean updated = userRepository.update(user);
        if (!updated) {
            throw new UserDataCouldNotBeUpdatedException();
        }
    }

    @RequestMapping(path = "/user/{id}", method = DELETE)
    @ApiOperation(value = "Delete a user")
    public void delete(@RequestParam Long id) {
        boolean deleted = userRepository.delete(id);
        if (!deleted) {
            throw new UserDataCouldNotBeUpdatedException();
        }
    }

    @RequestMapping(path = "/login", method = POST)
    @ApiOperation(value = "Logins a user")
    public void login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        boolean loggedIn = userRepository.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (loggedIn) {
            String sid = sessionRepository.create(userRepository.findByEmail(loginRequest.getEmail()));
            Cookie cookie = new Cookie("sid",sid);
            cookie.setPath("/");
            response.addCookie(cookie);
            System.out.println(sessionRepository.getUserID(sid));
        } else {
            throw new LoginFailedException();
        }


    }


//    @RequestMapping(path = "/login", method = OPTIONS)
//    @ResponseBody
//    @ApiOperation(value = "Logins a user")
//    public void loginOptions(HttpServletResponse response) {
//        response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//        response.addHeader("Access-Control-Allow-Methods", "*");
//        response.addHeader("Access-Control-Allow-Headers", "content-type");
//        response.addHeader("Access-Control-Allow-Credentials", "true");
//    }


    //@RequestMapping(path = "/login", method = GET)
    //@ApiOperation(value = "Logins a user")
//    public void login2(@RequestParam String email, @RequestParam String password, HttpServletResponse response) {
//        System.out.println(email+" "+password);
//        loginOptions(response);
//        login(new LoginRequest(email, password), response);
//    }

   @RequestMapping(path = "/logout", method = GET)
    @ApiOperation(value = "Logs out a user")
    public void logout(@RequestParam String sid, HttpServletResponse response) {
       System.out.println(sid);
       sessionRepository.destroy(sid);
       Cookie cookie = new Cookie("sid", sid);
       cookie.setMaxAge(0);
       response.addCookie(cookie);
    }
}
