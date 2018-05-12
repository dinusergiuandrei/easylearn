package ro.infoiasi.ip.easylearn.user.model;

public class SubmissionTest {

    private long submissionID;
    private long testID;
    private boolean passed;

    public SubmissionTest(long submissionID, long testID, boolean passed) {
        super();
        this.submissionID = submissionID;
        this.testID = testID;
        this.passed = passed;
    }

    public long getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(long submissionID) {
        this.submissionID = submissionID;
    }

    public long getTestID() {
        return testID;
    }

    public void setTestID(long testID) {
        this.testID = testID;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

}

