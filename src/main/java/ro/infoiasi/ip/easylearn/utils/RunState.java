package ro.infoiasi.ip.easylearn.utils;

public enum RunState {
    Failed("failed"),
    Success("success");
    String value;

    RunState(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
