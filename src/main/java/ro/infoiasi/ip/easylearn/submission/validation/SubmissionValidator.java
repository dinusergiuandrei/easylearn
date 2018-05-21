package ro.infoiasi.ip.easylearn.submission.validation;

import com.google.common.base.Strings;
import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionRequest;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;
import ro.infoiasi.ip.easylearn.utils.Language;

import java.util.Arrays;

@Component
public class SubmissionValidator {
    private final UserRepository userRepository;

    public SubmissionValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(SubmissionRequest submissionRequest){
        System.out.println("Validating submissionRequest: " + submissionRequest);

        if(submissionRequest.getProblemId() == null){
            throw new IllegalArgumentException("The problem id cannot be null");
        }

        if(submissionRequest.getUserID() == null){
            throw new IllegalArgumentException("The user id cannot be null");
        }

        if(!Arrays.asList(Language.values()).contains(submissionRequest.getLanguage())){
            throw new IllegalArgumentException("The submitted language is not valid");
        }

        if(Strings.isNullOrEmpty(submissionRequest.getMainSource())){
            throw new IllegalArgumentException("The main source is not valid");
        }
    }
}
