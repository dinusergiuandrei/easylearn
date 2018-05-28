package ro.infoiasi.ip.easylearn.submission.validation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.infoiasi.ip.easylearn.submission.model.SubmissionRequest;
import ro.infoiasi.ip.easylearn.utils.Language;

public class SubmissionValidatorTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private SubmissionValidator submissionValidator = new SubmissionValidator();

    @Test
    public void givenASubmissionWithANullProblemId_validate_throwsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The problem id cannot be null");
        submissionValidator.validate(new SubmissionRequest());
    }

    @Test
    public void givenASubmissionWithANullUserId_validate_throwsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The user id cannot be null");
        SubmissionRequest submissionRequest = createSubmission(1L, null, null, null);
        submissionValidator.validate(submissionRequest);
    }

    @Test
    public void givenASubmissionWithANullMainSource_validate_throwsException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("The main source is not valid");
        SubmissionRequest submissionRequest = createSubmission(1L, 1L, Language.Java, null);
        submissionValidator.validate(submissionRequest);
    }

    @Test
    public void givenARightSubmission_validate_works() {
        SubmissionRequest submissionRequest = createSubmission(1L, 1L, Language.Java, "main");
        submissionValidator.validate(submissionRequest);
    }

    private SubmissionRequest createSubmission(Long problemId, Long userId, Language language, String mainSource) {
        SubmissionRequest submissionRequest = new SubmissionRequest();
        submissionRequest.setProblemId(problemId);
        submissionRequest.setUserId(userId);
        submissionRequest.setLanguage(language);
        submissionRequest.setMainSource(mainSource);

        return submissionRequest;
    }
}