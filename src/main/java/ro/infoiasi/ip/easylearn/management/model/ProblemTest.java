package ro.infoiasi.ip.easylearn.management.model;

public class ProblemTest {
    private Long id;
    private Long problemId;
    private String input;
    private String expectedOutput;

    public ProblemTest(Long id, Long problemId, String input, String expectedOutput) {
        this.id = id;
        this.problemId = problemId;
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
}
