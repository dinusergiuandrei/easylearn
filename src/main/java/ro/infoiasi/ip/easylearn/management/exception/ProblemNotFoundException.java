package ro.infoiasi.ip.easylearn.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The problem does not exist")
public class ProblemNotFoundException extends RuntimeException{
}
