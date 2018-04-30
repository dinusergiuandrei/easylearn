package ro.infoiasi.ip.easylearn.utils;

import ro.infoiasi.ip.easylearn.compiler.Output;

public enum SubmissionState {
    Waiting("waiting"),
    Running("running"),
    Failed("failed"),
    Completed("completed");

    String string;

    SubmissionState(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }

    public static SubmissionState getSubmissionStateFromOutput(Output output){
        if(output.getError()!= null && output.getError().length() > 0){
            return Failed;
        }
        else return Completed;
    }
}
