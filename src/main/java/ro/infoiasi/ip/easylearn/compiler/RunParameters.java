package ro.infoiasi.ip.easylearn.compiler;

import ro.infoiasi.ip.easylearn.utils.Permission;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RunParameters {
    private String keyboardInput;
    private Long timeout;
    private TimeUnit timeUnit;

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

    @Override
    public String toString() {
        return "RunParameters{" +
                "keyboardInput='" + keyboardInput + '\'' +
                ", timeout=" + timeout +
                ", timeUnit=" + timeUnit +
                '}';
    }
}
