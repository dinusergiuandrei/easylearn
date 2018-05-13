package ro.infoiasi.ip.easylearn.compiler;

import java.util.concurrent.TimeUnit;

public class RunParameters {
    String keyboardInput;
    Long timeout;
    TimeUnit timeUnit;

    public RunParameters(String keyboardInput, Long timeout, TimeUnit timeUnit) {
        this.keyboardInput = keyboardInput;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public String getKeyboardInput() {
        return keyboardInput;
    }

    public Long getTimeout() {
        return timeout;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
