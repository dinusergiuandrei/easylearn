package ro.infoiasi.ip.easylearn.submission.validation;

import com.google.common.base.Strings;
import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionRequest;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;

@Component
public class SubmissionValidator {
    public SubmissionValidator() {

    }

    public void validate(SubmissionRequest submissionRequest) {
        System.out.println("Validating submissionRequest: " + submissionRequest);

        if (submissionRequest.getProblemId() == null) {
            throw new IllegalArgumentException("The problem id cannot be null");
        }

        if (submissionRequest.getUserId() == null) {
            throw new IllegalArgumentException("The user id cannot be null");
        }

//        the language field will not be validated
//       (beeing an Enum, the level of deserialization will check it)

        if (Strings.isNullOrEmpty(submissionRequest.getMainSource())) {
            throw new IllegalArgumentException("The main source is not valid");
        }
    }
}
