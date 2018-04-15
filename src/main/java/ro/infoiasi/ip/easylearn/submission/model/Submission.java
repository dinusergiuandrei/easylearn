package ro.infoiasi.ip.easylearn.submission.model;


public class Submission {
    public Long id;
    public Long problemId;
    public String language;
    public String sourceCode;

    // waiting, evaluating, completed
    public String state;

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", problemId=" + problemId +
                ", language='" + language + '\'' +
                ", sourceCode='" + sourceCode + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
