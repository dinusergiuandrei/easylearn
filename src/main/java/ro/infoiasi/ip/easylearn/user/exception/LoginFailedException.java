package ro.infoiasi.ip.easylearn.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Login failed")
public class LoginFailedException extends RuntimeException {
}
