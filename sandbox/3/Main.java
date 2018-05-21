import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {
   public static void main(String args[]) throws Exception {
     //SecurityManager securityManager = new SecurityManager();     //securityManager.checkExec("<<ALL FILES>>");
     //System.setSecurityManager(new SecurityManager());
     Process process = Runtime.getRuntime().exec("java -cp E:/Projects/easylearn/sandbox/3/sources Main");
     addKeyboardInput(process, "");
     Output output = getProcessOutput(process, 10L,  TimeUnit.MILLISECONDS );
     FileWriter fileWriter = new FileWriter("E:/Projects/easylearn/sandbox/3/output/error");     PrintWriter writer = new PrintWriter(fileWriter);
     writer.println(output.getError());
     writer.close();
     fileWriter = new FileWriter("E:/Projects/easylearn/sandbox/3/output/output");     writer = new PrintWriter(fileWriter);
     writer.println(output.getOutput());
     writer.close();
     System.out.println(output.toString());
     //System.setSecurityManager(null);
   }
   public static String getStringFromInputStream(InputStream ins) throws Exception {
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
    public static void addKeyboardInput(Process process, String input) throws IOException {
        if (input != null && input.length() > 0) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(input);
            writer.flush();
        }
    }

}
