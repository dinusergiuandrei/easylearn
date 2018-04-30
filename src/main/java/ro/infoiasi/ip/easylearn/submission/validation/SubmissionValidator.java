package ro.infoiasi.ip.easylearn.submission.validation;

import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionRequest;

@Component
public class SubmissionValidator {
    public void validate(SubmissionRequest submissionRequest){
        // implement validation logic, throw exception if not valid
        System.out.println("Validating submissionRequest: " + submissionRequest);
    }
}
