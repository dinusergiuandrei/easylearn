package ro.infoiasi.ip.easylearn.user.model;

public class Submissions {

    private long submissionID;
    private long userID;
    private long problemID;
    private String language;
    private String state;
    private String Date;


    public Submissions(int submissionID, int userID, int problemID, String sourceCode, String language, String state,
                       String date) {
        super();
        this.submissionID = submissionID;
        this.userID = userID;
        this.problemID = problemID;
        this.language = language;
        this.state = state;
        Date = date;
    }

    public long getSubmissionID() {
        return submissionID;
    }

    public void setSubmissionID(int submissionID) {
        this.submissionID = submissionID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public long getProblemID() {
        return problemID;
    }

    public void setProblemID(int problemID) {
        this.problemID = problemID;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

}
