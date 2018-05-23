package ro.infoiasi.ip.easylearn.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "The user data could not be updated")
public class UserDataCouldNotBeUpdatedException extends RuntimeException {
}
