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

    public UserController(UserRepository userRepository, SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
    }

    @RequestMapping(path = "/users", method = GET)
    @ResponseBody
    @ApiOperation(value = "View the information about all the users")
    public List<User> users() {
        ConsoleLogger.Log("Get all users");
        return userRepository.findAll();
    }

    @RequestMapping(path = "/users", method = POST)
    @ResponseBody
    @ApiOperation(value = "Registers the user")
    public UserResponse register(@RequestBody User user) {
        Long id = userRepository.register(user);

        ConsoleLogger.Log("Register: " + user);

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
        Long uid = MustBeLoggedIn(request);
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
        Long uid = MustBeLoggedIn(request);
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
        Long uid = MustBeLoggedIn(request);
        ConsoleLogger.Log("Profile UID: " + uid);
        return userRepository.findById(uid);
    }

    public Long MustBeLoggedIn(HttpServletRequest request) {
        Cookie cookie = CookieManager.getCookie(request.getCookies(), "sid");
        if (cookie != null && sessionRepository.isActive(cookie.getValue()))
            return sessionRepository.getUserID(cookie.getValue());
        else throw new NotLoggedInException();
    }


    @RequestMapping(path = "/logout", method = GET)
    @ApiOperation(value = "Logs out current user")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieManager.getCookie(request.getCookies(), "sid");
        ConsoleLogger.Log("Logout SID: " + cookie.getValue());

        if (cookie == null) return;
        sessionRepository.destroy(cookie.getValue());
        Cookie _cookie = new Cookie("sid", cookie.getValue());
        _cookie.setMaxAge(0);
        _cookie.setPath("/");
        response.addCookie(cookie);
    }

    @RequestMapping(path = "/forgot", method= POST)
    @ResponseBody
    @ApiOperation(value = "Reset user password")
    public String forgot(@RequestBody ForgotUser forgotUser, HttpServletRequest request) {
        Long uid = MustBeLoggedIn(request);
        forgotUser.setId(uid);
        String newPassword = userRepository.forgot(forgotUser);
        if(newPassword == null)
            throw new UserDataCouldNotBeUpdatedException();
        return newPassword;
    }

}
