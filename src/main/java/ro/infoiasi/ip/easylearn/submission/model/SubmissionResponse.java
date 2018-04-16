package ro.infoiasi.ip.easylearn.submission.model;

// de hotarat modelul pentru raspuns
public class SubmissionResponse {
    private String result;

    public String getResult() {
        return result;
    }

    public SubmissionResponse(Long id) {
        this.result = "Processed submission http://localhost:8100/submissions/" + id;
    }
}
