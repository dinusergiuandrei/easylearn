package ro.infoiasi.ip.easylearn.compiler;

public class Output {
    private String error;

    private String output;

    private Boolean success;

    private Integer exitValue;

    Integer getExitValue() {
        return exitValue;
    }

    void setExitValue(Integer exitValue) {
        this.exitValue = exitValue;
    }

    Boolean getSuccess() {
        return success;
    }

    void setSuccess(Boolean success) {
        this.success = success;
    }

    String getError() {
        return error;
    }

    void setError(String error) {
        this.error = error;
    }

    String getOutput() {
        return output;
    }

    void setOutput(String output) {
        this.output = output;
    }
}
