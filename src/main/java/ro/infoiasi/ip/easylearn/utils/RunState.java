package ro.infoiasi.ip.easylearn.utils;

public enum RunState {
    Failed("Failed"),
    Success("Success");
    String value;

    RunState(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
