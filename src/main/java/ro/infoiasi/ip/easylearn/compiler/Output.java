package ro.infoiasi.ip.easylearn.compiler;

public class Output {
    private String error;

    private String output;

    private Integer exitValue;

    public static Output getSuccessOutput() {
        Output output = new Output();
        output.setExitValue(0);
        return output;
    }

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

    public static Output getOutputFromMessage(String message) {
        String errorTag = "Run error: ";
        String exitValueTag = "Exit value: ";
        if (message.startsWith(exitValueTag)) {
            Output output = new Output();
            Integer exitValue = Integer.parseInt(message.substring(exitValueTag.length()).trim());
            output.setExitValue(exitValue);
            output.setOutput("");
            output.setError("");
            return output;
        }
        if (message.startsWith(errorTag)) {
            Output output = new Output();
            Integer errorStartIndex = message.indexOf(errorTag);

            String errorMessage;
            Integer exitValueIndex = message.indexOf(exitValueTag);

            if (exitValueIndex >= 0) {
                Integer exitValue = Integer.parseInt(message.substring(exitValueIndex + exitValueTag.length()).trim());
                output.setExitValue(exitValue);
                errorMessage = message.substring(errorStartIndex + errorTag.length(), exitValueIndex).trim();
            } else {
                errorMessage = message.substring(errorStartIndex + errorTag.length()).trim();
            }

            output.setError(errorMessage);
            output.setOutput("");
            return output;
        }
        Output output = new Output();
        String outputMessage;
        Integer exitValueIndex = message.indexOf(exitValueTag);
        if (exitValueIndex >= 0) {
            Integer exitValue = Integer.parseInt(message.substring(exitValueIndex + exitValueTag.length()).trim());
            output.setExitValue(exitValue);
            outputMessage = message.substring(0, exitValueIndex).trim();
        } else {
            outputMessage = message.trim();
            output.setExitValue(0);
        }
        output.setOutput(outputMessage);
        output.setError("");
        return output;
    }

    public Integer getExitValue() {
        return exitValue;
    }

    public void setExitValue(Integer exitValue) {
        this.exitValue = exitValue;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}

