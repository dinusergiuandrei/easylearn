package ro.infoiasi.ip.easylearn.submission.validation;

import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionRequest;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;

@Component
public class SubmissionValidator {
    private final UserRepository userRepository;

    public SubmissionValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(SubmissionRequest submissionRequest){
        // implement validation logic, throw exception if not valid
        System.out.println("Validating submissionRequest: " + submissionRequest);
    }
}
