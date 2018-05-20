import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class Main {
   public static void main(String args[]) throws Exception {
     //SecurityManager securityManager = new SecurityManager();     //securityManager.checkExec("<<ALL FILES>>");
     //System.setSecurityManager(new SecurityManager());
     Process process = Runtime.getRuntime().exec("java -cp sandbox/3/sources Main");
     ProcessManager.addKeyboardInput(process, "");
     Output output = ProcessManager.getProcessOutput(process, 10L,  TimeUnit.MILLISECONDS );
     PrintWriter writer = new PrintWriter("sandbox/3/output/error", "UTF-8");
     writer.println(output.getError());
     writer.close();
     writer = new PrintWriter("sandbox/3/output/output", "UTF-8");
     writer.println(output.getOutput());
     writer.close();
     //System.setSecurityManager(null);
   }
}
