package ro.infoiasi.ip.easylearn.compiler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ro.infoiasi.ip.easylearn.compiler.Compiler;
import ro.infoiasi.ip.easylearn.compiler.CompilerParameters;
import ro.infoiasi.ip.easylearn.compiler.Output;

import java.security.AccessControlException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class CompilerTest {
    private static Compiler compiler;

    private static CompilerParameters helloWorldParameters;

    private static CompilerParameters fileWriterParameters;

    private static String runOutputPath = "sandbox/sample/run_output/java/output_file.txt";

    @Before
    public void setUp() {
        compiler = new Compiler();

        helloWorldParameters = new CompilerParameters(
                "sandbox/sample/sources/java/HelloWorld.java",
                "sandbox/sample/generated/java",
                "",
                10L,
                TimeUnit.SECONDS
        );

        fileWriterParameters = new CompilerParameters(
                "sandbox/sample/sources/java/FileWriter.java",
                "sandbox/sample/generated/java",
                "",
                10L,
                TimeUnit.SECONDS
        );
    }

    @Test
    public void helloWorldTest() {
        Output output = compileAndRun(helloWorldParameters);
        Assert.assertEquals("Hello World!", output.getOutput().trim());
    }

    //@Ignore("Gradle hangs when a test accesses the security manager: https://github.com/gradle/gradle/issues/3526")
    @Test
    public void writeTest() {
        try {
            compiler
                    .getSecurityManager()
                    .checkWrite(runOutputPath);
            compileAndRun(fileWriterParameters);
        } catch (AccessControlException e) {
            String message
                    = e.getMessage()
                    .replace("(", "")
                    .replace("\"", "");
            String expectedRegex = "(access denied java.io.FilePermission )(.*)( write)";
            Pattern p = Pattern.compile(expectedRegex);
            Matcher matcher = p.matcher(message);
            Assert.assertTrue(matcher.find());
            return;
        }
        Assert.fail();
    }

    public Output compileAndRun(CompilerParameters parameters) {
        try {
            Output compileOutput = compiler.compile(parameters);
            if (compileOutput.getExitValue() == 0) {
                Output runOutput = compiler.run(parameters);
                return runOutput;
            } else {
                return compileOutput;
            }
        } catch (Exception e) {
            Output errorOutput = new Output();
            errorOutput.setError(e.getMessage());
            return errorOutput;
        }
    }
}
