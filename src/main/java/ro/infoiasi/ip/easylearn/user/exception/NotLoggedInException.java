package ro.infoiasi.ip.easylearn.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Not logged id")
public class NotLoggedInException extends RuntimeException {
}
