package ro.infoiasi.ip.easylearn.submission.validation;

import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.submission.model.Submission;

@Component
public class SubmissionValidator {
    public void validate(Submission submission){
        // implement validation logic, throw exception if not valid
        System.out.println("Validating submission: " + submission);
    }
}
