package ro.infoiasi.ip.easylearn.Compiler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ro.infoiasi.ip.easylearn.compiler.Compiler;
import ro.infoiasi.ip.easylearn.compiler.CompilerParameters;
import ro.infoiasi.ip.easylearn.compiler.Output;

import java.security.AccessControlException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompilerTest {
    //todo: find out why projectRootPath is not "."

    private static Compiler compiler;

    private static CompilerParameters helloWorldParameters;

    private static CompilerParameters fileWriterParameters;

    @Before
    public void setUp() {
        compiler = new Compiler();

        helloWorldParameters = new CompilerParameters(
                "sample/sources/java/HelloWorld.java",
                "sample/compile_output/java",
                "",
                "../../",
                10L,
                TimeUnit.SECONDS
        );

        fileWriterParameters = new CompilerParameters(
                "ro/infoiasi/ip/easylearn/compiler/FileWriter.java",
                "sample/compile_output/java",
                "",
                "../../",
                10L,
                TimeUnit.SECONDS
        );
    }

    @Test
    public void helloWorldTest() {
        Output output = compiler.compileAndRun(helloWorldParameters);
        System.out.println(output);
        Assert.assertEquals(output.getOutput().trim(), "Hello World!");
    }

    @Test
    public void writeTest() {
        try {
            compiler
                    .getSecurityManager()
                    .checkWrite(
                            fileWriterParameters.getProjectRootPath()
                                    + fileWriterParameters.getCompileOutputPath()
                    );
            Output output = compiler.compileAndRun(fileWriterParameters);
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
}
