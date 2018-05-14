package ro.infoiasi.ip.easylearn.compiler;

import java.io.*;
import java.util.concurrent.TimeUnit;

import static ro.infoiasi.ip.easylearn.utils.Strings.getStringFromInputStream;

public abstract class Compiler {

    public abstract Output compile(CompilerParameters parameters) throws Exception;

    public abstract Output run(SourceFile mainClass, CompilerParameters compilerParameters, RunParameters runParameters) throws Exception;

    public void setUpRootDirectory(String rootDirectoryPath) throws IOException {
        new File(rootDirectoryPath).mkdir();
    }

    void addSourceToFile(String source, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            writer.write(source);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    void addKeyboardInput(Process process, String input) throws IOException {
        if (input != null && input.length() > 0) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(input);
            writer.flush();
        }
    }

    Output getProcessOutput(Process process) throws Exception {
        process.waitFor();
        return getProcessRunOutput(process);
    }

    Output getProcessOutput(Process process, Long timeout, TimeUnit timeUnit) throws Exception {
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
}
