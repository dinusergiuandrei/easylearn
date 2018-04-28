package ro.infoiasi.ip.easylearn.compiler;

import java.io.*;

/**
 * Do not use yet. Path issues.
 */
public class Compiler {

    private SecurityManager securityManager;

    private Output compileOutput;

    private Output runOutput;

    public void compileAndRun(String sourcePath) {

        String inputStream = null;

        try {
            compileOutput = compile(sourcePath);
            if (compileOutput.getSuccess()) {
                runOutput = run(this.extractClassNameFromSourcePath(sourcePath), inputStream);
            }
            this.displayResults();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayResults() {
        if(compileOutput!=null) {
            System.out.println("Compile output: " + (compileOutput.getOutput()));
            System.out.println("Compile error: \n" + (this.compileOutput.getError()));
            System.out.println("Compiled: " + this.compileOutput.getSuccess());
        }
        if(runOutput!=null) {
            System.out.println();
            System.out.println("Run output: \n" + runOutput.getOutput());
            System.out.println("Run error: \n" + runOutput.getError());
            System.out.println("Exit value: \n" + runOutput.getExitValue());
        }
    }

    private Output compile(String mainFile) throws Exception {
        String command = "javac " + mainFile;
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();

        Output compileOutput = new Output();
        compileOutput.setError(getStringFromInputStream(process.getErrorStream()));
        compileOutput.setOutput(getStringFromInputStream(process.getInputStream()));
        compileOutput.setExitValue(process.exitValue());
        if (compileOutput.getExitValue() == 0) {
            compileOutput.setSuccess(true);
        } else {
            compileOutput.setSuccess(false);
        }

        return compileOutput;
    }

    private Output run(String mainFile, String input) throws Exception {
        String command = "java " + mainFile;
        Process process = Runtime.getRuntime().exec(command);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        writer.write(input);
        writer.flush();

        process.waitFor();

        Output runOutput = new Output();
        runOutput.setError(getStringFromInputStream(process.getErrorStream()));
        runOutput.setOutput(getStringFromInputStream(process.getInputStream()));
        runOutput.setExitValue(process.exitValue());
        if (runOutput.getExitValue() == 0) {
            runOutput.setSuccess(true);
        } else {
            runOutput.setSuccess(false);
        }

        return runOutput;
    }

    public void loadSecurityManager() {
        SecurityManager securityManager = new SecurityManager();
        System.setSecurityManager(securityManager);
        this.securityManager = System.getSecurityManager();

        //security.checkRead(sourcePath);
        //security.checkWrite(outputPath);
    }

    private String extractClassNameFromSourcePath(String sourcePath) {
        if (sourcePath.endsWith(".java")) {
            return sourcePath.substring(0, sourcePath.length() - 5);
        } else {
            System.err.println("Source path does not point to a java file: " + sourcePath);
        }
        return null;
    }

    private static String getStringFromInputStream(InputStream ins) throws Exception {
        StringBuilder s = new StringBuilder();
        String line;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            s.append(line);
            s.append('\n');
        }

        return s.toString();
    }

    public Output getCompileOutput() {
        return compileOutput;
    }

    public Output getRunOutput() {
        return runOutput;
    }
}
