package ro.infoiasi.ip.easylearn.submission.model;


public class Submission {
    public long id;
    public long problemId;
    public String language;
    public String sourceCode;

    // waiting, evaluating, completed
    public String state;
}
