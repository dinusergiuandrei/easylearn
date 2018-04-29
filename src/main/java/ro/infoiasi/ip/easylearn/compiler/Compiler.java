package ro.infoiasi.ip.easylearn.compiler;

import java.io.*;
import java.util.concurrent.TimeUnit;

import static ro.infoiasi.ip.easylearn.utils.Strings.extractClassNameFromSourcePath;
import static ro.infoiasi.ip.easylearn.utils.Strings.getStringFromInputStream;

public class Compiler {

    private SecurityManager securityManager;

    public Output compileAndRun(CompilerParameters parameters) {

        Output compileOutput;
        Output runOutput;

        try {
            compileOutput = compile(parameters);
        } catch (Exception e) {
            Output errorOutput = new Output();
            errorOutput.setError(e.getMessage());
            return errorOutput;
        }

        if (compileOutput.getExitValue() == 0) {
            try {
                runOutput = run(parameters);
            } catch (Exception e) {
                Output errorOutput = new Output();
                errorOutput.setError(e.getMessage());
                return errorOutput;
            }
            return runOutput;
        } else {
            return compileOutput;
        }
    }

    private Output compile(CompilerParameters parameters) throws Exception {
        String command
                = "javac -d "
                + parameters.getProjectRootPath()
                + parameters.getCompileOutputPath() + " "
                + parameters.getProjectRootPath()
                + parameters.getSourcePath();

        System.out.println(command);
        Process process = Runtime.getRuntime().exec(command);

        return getProcessOutput(process, parameters.getTimeout(), parameters.getTimeUnit());
    }

    private Output run(CompilerParameters parameters) throws Exception {
        String className = extractClassNameFromSourcePath(parameters.getSourcePath());

        String command
                = "java -cp "
                + parameters.getProjectRootPath()
                + parameters.getCompileOutputPath() + " "
                + className;

        Process process = Runtime.getRuntime().exec(command);

        if (parameters.getKeyboardInput() != null && parameters.getKeyboardInput().length() > 0) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(parameters.getKeyboardInput());
            writer.flush();
        }

        return getProcessOutput(process, parameters.getTimeout(), parameters.getTimeUnit());
    }

    private Output getProcessOutput(Process process, Long timeout, TimeUnit timeUnit) throws Exception {
        process.waitFor(timeout, timeUnit);

        Output runOutput = new Output();
        runOutput.setError(getStringFromInputStream(process.getErrorStream()));
        runOutput.setOutput(getStringFromInputStream(process.getInputStream()));
        runOutput.setExitValue(process.exitValue());

        return runOutput;
    }

    public void loadSecurityManager() {
        SecurityManager securityManager = new SecurityManager();
        System.setSecurityManager(securityManager);
        this.securityManager = System.getSecurityManager();
    }

    public SecurityManager getSecurityManager() {
        if (this.securityManager == null) {
            this.loadSecurityManager();
        }
        return securityManager;
    }
}
