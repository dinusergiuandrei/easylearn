package ro.infoiasi.ip.easylearn.utils;

import ro.infoiasi.ip.easylearn.compiler.Output;

public enum SubmissionState {
    Waiting("Waiting"),
    Running("Running"),
    CompilationFailed("CompilationFailed"),
    Failed("Failed"),
    Completed("Completed");

    String value;

    SubmissionState(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static SubmissionState getSubmissionStateFromOutput(Output output){
        if(output.getError()!= null && output.getError().length() > 0){
            return CompilationFailed;
        }
        else return Completed;
    }
}
