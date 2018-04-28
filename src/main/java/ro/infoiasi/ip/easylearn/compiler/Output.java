package ro.infoiasi.ip.easylearn.compiler;
public class Output {
    private String error;

    private String output;

    private Boolean success;

    private Integer exitValue;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.output != null && this.output.length() > 0) {
            stringBuilder.append(this.output);
        }
        if (this.error != null && this.error.length() > 0) {
            stringBuilder.append("Run error: \n").append(this.error);
        }
        stringBuilder.append("Exit value: ").append(this.exitValue);

        return stringBuilder.toString();
    }

    Integer getExitValue() {
        return exitValue;
    }

    void setExitValue(Integer exitValue) {
        this.exitValue = exitValue;
    }

    String getError() {
        return error;
    }

    void setError(String error) {
        this.error = error;
    }

    public String getOutput() {
        return output;
    }

    void setOutput(String output) {
        this.output = output;
    }
}

