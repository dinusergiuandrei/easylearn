package ro.infoiasi.ip.easylearn.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "The user could not be deleted")
public class UserDataCouldNotBeDeletedException extends RuntimeException{
}
