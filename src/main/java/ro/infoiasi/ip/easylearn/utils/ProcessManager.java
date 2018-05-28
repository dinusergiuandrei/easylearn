package ro.infoiasi.ip.easylearn.utils;

import ro.infoiasi.ip.easylearn.compiler.Output;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;

import static ro.infoiasi.ip.easylearn.utils.Strings.getStringFromInputStream;

public abstract class ProcessManager {

    public static Process runCommand(String command) throws IOException {
        return runCommandRuntime(command);
    }

    private static Process runCommandRuntime(String command) throws IOException {
        return Runtime.getRuntime().exec(command);
    }

    public static String removePathEnd(String path){
        return path.substring(0, path.indexOf('.'));
    }

    public static void addKeyboardInput(Process process, String input) throws IOException {
        if (input != null && input.length() > 0) {
            System.out.println("Providing the input of the test to the run: " + input);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(input);
            writer.newLine();
            writer.flush();
            System.out.println("Provided the input.");
        } else {
            System.out.println("There is no input to provide for this test: " + input);
        }
    }

    public static Output getProcessOutput(Process process) throws Exception {
        process.waitFor();
        return getProcessRunOutput(process);
    }

    public static Output getProcessOutput(Process process, Long timeout, TimeUnit timeUnit) throws Exception {
        process.waitFor(timeout, timeUnit);
        return getProcessRunOutput(process);
    }

    private static Output getProcessRunOutput(Process process) throws Exception {
        Output runOutput = new Output();
        runOutput.setError(getStringFromInputStream(process.getErrorStream()));
        runOutput.setOutput(getStringFromInputStream(process.getInputStream()));
        runOutput.setExitValue(process.exitValue());

        return runOutput;
    }
}
