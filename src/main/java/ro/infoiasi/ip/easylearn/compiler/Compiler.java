package ro.infoiasi.ip.easylearn.compiler;

import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.utils.Language;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static ro.infoiasi.ip.easylearn.utils.Strings.getStringFromInputStream;

public class Compiler {

    private SecurityManager securityManager;

    public Output compile(CompilerParameters parameters) throws Exception {
        String command = null;

        Language language = parameters.getLanguage();

        for (SourceFile sourceFile : parameters.getSourceCodes()) {
            addSourceToFile(sourceFile.getContent(), parameters.getCompileOutputPath() + System.getProperty("file.separator") + sourceFile.getTitle());
        }

        switch (language) {
            case Java:
                command = "javac -d " + parameters.getCompileOutputPath() + " " + parameters.getCompileOutputPath() + System.getProperty("file.separator") + "*.java";
                break;
        }

        Process process = Runtime.getRuntime().exec(command);

        return getProcessOutput(process);
    }

    public Output run(SourceFile mainClass, CompilerParameters compilerParameters, RunParameters runParameters) throws Exception {

        Language language = compilerParameters.getLanguage();

        String command = null;

        switch (language) {
            case Java:
                String title = null;
                if(mainClass.getTitle().endsWith(".java")){
                    title = mainClass.getTitle().substring(0, mainClass.getTitle().length() - 5);
                }
                command = "java -cp " + compilerParameters.getCompileOutputPath() + " " + title;
                break;
        }

        Process process = Runtime.getRuntime().exec(command);

        addKeyboardInput(process, runParameters.getKeyboardInput());

        return getProcessOutput(process, runParameters.getTimeout(), runParameters.getTimeUnit());
    }

    private void addSourceToFile(String source, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            writer.write(source);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addKeyboardInput(Process process, String input) throws IOException {
        if (input != null && input.length() > 0) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(input);
            writer.flush();
        }
    }

    private Output getProcessOutput(Process process) throws Exception {
        process.waitFor();
        return getProcessRunOutput(process);
    }

    private Output getProcessOutput(Process process, Long timeout, TimeUnit timeUnit) throws Exception {
        process.waitFor(timeout, timeUnit);
        return getProcessRunOutput(process);
    }

    private Output getProcessRunOutput(Process process) throws Exception {
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
