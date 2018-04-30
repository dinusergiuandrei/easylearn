package ro.infoiasi.ip.easylearn.compiler;

import java.util.concurrent.TimeUnit;

public class CompilerParameters {
    private String sourcePath;
    private String compileOutputPath;
    private String keyboardInput;
    private Long timeout;
    private TimeUnit timeUnit;

    public CompilerParameters(String sourcePath, String compileOutputPath, String keyboardInput, Long timeout, TimeUnit timeUnit) {
        this.sourcePath = sourcePath;
        this.compileOutputPath = compileOutputPath;
        this.keyboardInput = keyboardInput;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getCompileOutputPath() {
        return compileOutputPath;
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
