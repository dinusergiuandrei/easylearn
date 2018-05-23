package ro.infoiasi.ip.easylearn.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The user does not exist")
public class UserNotFoundException extends RuntimeException{
}
