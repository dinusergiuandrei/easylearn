package ro.infoiasi.ip.easylearn.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;
import ro.infoiasi.ip.easylearn.user.exception.*;
import ro.infoiasi.ip.easylearn.user.model.ForgotUser;
import ro.infoiasi.ip.easylearn.user.model.LoginRequest;
import ro.infoiasi.ip.easylearn.user.model.User;
import ro.infoiasi.ip.easylearn.user.model.UserResponse;
import ro.infoiasi.ip.easylearn.user.repository.api.SessionRepository;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;
import ro.infoiasi.ip.easylearn.user.validation.UserValidator;
import ro.infoiasi.ip.easylearn.utils.ConsoleLogger;
import ro.infoiasi.ip.easylearn.utils.CookieManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@Api(description = "Operations pertaining to the manipulations of users")
public class UserController {

    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    private UserValidator userValidator;

    public UserController(UserRepository userRepository, SessionRepository sessionRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.userValidator = userValidator;
    }

    @RequestMapping(path = "/users", method = GET)
    @ResponseBody
    @ApiOperation(value = "View the information about all the users")
    public List<User> users(HttpServletRequest request) {
        ConsoleLogger.Log("Get all users");
        sessionRepository.MustBeLoggedIn(request);
        return userRepository.findAll();
    }

    @RequestMapping(path = "/users", method = POST)
    @ResponseBody
    @ApiOperation(value = "Registers the user")
    public UserResponse register(@RequestBody User user) {
        ConsoleLogger.Log("Register: " + user);

        if (!userValidator.validateUserRegister(user))
            throw new RegistrationFailedException();

        Long id = userRepository.register(user);

        if (id == null) {
            throw new RegistrationFailedException();
        }

        return new UserResponse(id);
    }

    @RequestMapping(path = "/users/{id}", method = GET)
    @ResponseBody
    @ApiOperation(value = "View information about a particular user")
    public User user(@PathVariable Long id, HttpServletRequest request) throws UserNotFoundException {
        sessionRepository.MustBeLoggedIn(request);
        User user = userRepository.findById(id);
        ConsoleLogger.Log("Get user info ID: " + id);
        if (user == null) {
            throw new UserNotFoundException();
        } else {
            return user;
        }
    }

    @RequestMapping(path = "/users", method = PUT)
    @ApiOperation(value = "Update current user's data")
    public void update(@RequestBody User user, HttpServletRequest request) {
        if (!userValidator.validateUserUpdate(user))
            throw new UserDataCouldNotBeUpdatedException();

        Long uid = sessionRepository.MustBeLoggedIn(request);
        user.setId(uid);

        boolean updated = userRepository.update(user);

        ConsoleLogger.Log("Update: " + user + " | Status: " + updated);

        if (!updated) {
            throw new UserDataCouldNotBeUpdatedException();
        }
    }

    @RequestMapping(path = "/user", method = DELETE)
    @ApiOperation(value = "Delete current user")
    public void delete(HttpServletRequest request) {
        Long uid = sessionRepository.MustBeLoggedIn(request);
        ConsoleLogger.Log("Delete user UID: " + uid);
        boolean deleted = userRepository.delete(uid);

        //Todo: destroy session

        if (!deleted) {
            throw new UserDataCouldNotBeUpdatedException();
        }
    }

    @RequestMapping(path = "/login", method = POST)
    @ApiOperation(value = "Logins a user")
    public void login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        boolean loggedIn = userRepository.login(loginRequest.getEmail(), loginRequest.getPassword());

        ConsoleLogger.Log(loginRequest + " | " + loggedIn);

        if (loggedIn) {
            String sid = sessionRepository.create(userRepository.findByEmail(loginRequest.getEmail()));
            Cookie cookie = new Cookie("sid", sid);
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            throw new LoginFailedException();
        }
    }

    @RequestMapping(path = "/profile", method = GET)
    @ResponseBody
    @ApiOperation(value = "Get current user info")
    public User profile(HttpServletRequest request) {
        Long uid = sessionRepository.MustBeLoggedIn(request);
        ConsoleLogger.Log("Profile UID: " + uid);
        return userRepository.findById(uid);
    }

    @RequestMapping(path = "/logout", method = POST)
    @ApiOperation(value = "Logs out current user")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieManager.getCookie(request.getCookies(), "sid");
        ConsoleLogger.Log("Logout");
        if (cookie == null) return;
        ConsoleLogger.Log("Logout SID: " + cookie.getValue());
        sessionRepository.destroy(cookie.getValue());


//        SetMaxAge don't work

//        Cookie _cookie = new Cookie("sid", null);
//        _cookie.setMaxAge(0);
//        _cookie.setPath("/");
//        response.addCookie(cookie);
    }

    @RequestMapping(path = "/forgot", method = POST)
    @ResponseBody
    @ApiOperation(value = "Reset user password")
    public String forgot(@RequestBody ForgotUser forgotUser, HttpServletRequest request) {
        String newPassword = userRepository.forgot(forgotUser);

        ConsoleLogger.Log(forgotUser.toString() + "  New: " + newPassword);

        if (newPassword == null)
            throw new UserDataCouldNotBeUpdatedException();
        return newPassword;
    }
}
