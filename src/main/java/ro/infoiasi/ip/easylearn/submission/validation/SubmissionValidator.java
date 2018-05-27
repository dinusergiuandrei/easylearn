package ro.infoiasi.ip.easylearn.submission.validation;

import com.google.common.base.Strings;
import org.springframework.stereotype.Component;
import ro.infoiasi.ip.easylearn.compiler.SourceFile;
import ro.infoiasi.ip.easylearn.management.model.Problem;
import ro.infoiasi.ip.easylearn.management.repository.api.ProblemRepository;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionRequest;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;

@Component
public class SubmissionValidator {
    private ProblemRepository problemRepository;

    public SubmissionValidator(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public void validate(SubmissionRequest submissionRequest) {
        System.out.println("Validating submissionRequest: " + submissionRequest);

        if (submissionRequest.getProblemId() == null) {
            throw new IllegalArgumentException("The problem id cannot be null");
        }

        Problem problem = problemRepository.findById(submissionRequest.getProblemId());
        if(problem == null)
            throw new IllegalArgumentException("Invalid problem id");

        if (submissionRequest.getUserId() == null) {
            throw new IllegalArgumentException("The user id cannot be null");
        }

//        the language field will not be validated
//       (beeing an Enum, the level of deserialization will check it)

        if (Strings.isNullOrEmpty(submissionRequest.getMainSource())) {
            throw new IllegalArgumentException("The main source is not valid");
        }

        if (submissionRequest.getSources() == null || submissionRequest.getSources().size() == 0)
            throw new IllegalArgumentException("Must be at least one source");

        boolean foundMain = false;
        for (SourceFile source : submissionRequest.getSources()) {
            if (source.getFileName().equals(submissionRequest.getMainSource())) {
                foundMain = true;
                break;
            }
            if(Strings.isNullOrEmpty(source.getFileName()) || Strings.isNullOrEmpty(source.getContent()))
                throw new IllegalArgumentException("Invalid source file");
        }
        if(!foundMain)
            throw new IllegalArgumentException("MainFile not found");
    }
}
