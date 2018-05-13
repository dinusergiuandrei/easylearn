package ro.infoiasi.ip.easylearn.compiler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ro.infoiasi.ip.easylearn.utils.Language;

import java.io.File;
import java.security.AccessControlException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class CompilerTest {
    private static Compiler compiler;

    private static CompilerParameters helloWorldCompileParameters;

    private static RunParameters helloWorldRunParameters;

    private static CompilerParameters fileWriterCompileParameters;

    private static RunParameters fileWriterRunParameters;

    private static CompilerParameters multipleFilesCompileParameters;

    private static RunParameters multipleFilesRunParameters;

    private static String runOutputPath;

    @Before
    public void setUp() {
        compiler = new Compiler();

        setUpHelloWorld();

        setUpFileWriter();

        setUpMultipleFiles();
    }

    private void setUpHelloWorld() {
        SourceFile helloWorldSourceFile = new SourceFile(
                "HelloWorld.java",
                "class HelloWorld {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        System.out.println(\"Hello World!\");\n" +
                        "    }\n" +
                        "}"
        );

        List<SourceFile> helloWorldSources = new LinkedList<>();

        helloWorldSources.add(helloWorldSourceFile);

        helloWorldCompileParameters = new CompilerParameters(
                Language.Java,
                helloWorldSources,
                "sandbox/1"
        );

        helloWorldRunParameters = new RunParameters(
                "",
                10L,
                TimeUnit.SECONDS
        );
    }

    private void setUpFileWriter() {
        SourceFile fileWriterSourceFile = new SourceFile(
                "FileWriter.java",
                "import java.io.*;\n" +
                        "\n" +
                        "class FileWriter {\n" +
                        "    public static void main(String[] args) throws FileNotFoundException {\n" +
                        "        PrintWriter writer = new PrintWriter(\"sandbox/2/output_file.txt\");\n" +
                        "        writer.println(\"The first line\");\n" +
                        "        writer.flush();\n" +
                        "        writer.close();\n" +
                        "    }\n" +
                        "}"
        );

        List<SourceFile> fileWriterSources = new LinkedList<>();

        fileWriterSources.add(fileWriterSourceFile);

        new File("sandbox/2").mkdir();
        fileWriterCompileParameters = new CompilerParameters(
                Language.Java,
                fileWriterSources,
                "sandbox/2"
        );

        fileWriterRunParameters = new RunParameters(
                "",
                10L,
                TimeUnit.SECONDS
        );

        runOutputPath = "sandbox/2/output_file.txt";
    }

    private void setUpMultipleFiles() {
        SourceFile main = new SourceFile(
                "Main.java",
                "public class Main {\n" +
                        "    public static void main(String[] args){\n" +
                        "        System.out.println(\"Hello from main\");\n" +
                        "        A a = new A();\n" +
                        "        a.hello();\n" +
                        "    }\n" +
                        "}\n"
        );

        SourceFile a = new SourceFile(
                "A.java",
                "public class A {\n" +
                        "    public void hello(){\n" +
                        "        System.out.println(\"Hello from A\");\n" +
                        "    }\n" +
                        "}\n"
        );

        List<SourceFile> multipleSources = new LinkedList<>();

        multipleSources.add(main);
        multipleSources.add(a);

        new File("sandbox/3").mkdir();
        multipleFilesCompileParameters = new CompilerParameters(
                Language.Java,
                multipleSources,
                "sandbox/3"
        );

        multipleFilesRunParameters = new RunParameters(
                "",
                10L,
                TimeUnit.SECONDS
        );
    }

    @Test
    public void helloWorldTest() {
        Output output = compileAndRun(helloWorldCompileParameters.getSourceCodes().get(0), helloWorldCompileParameters, helloWorldRunParameters);
        System.out.println(output.getError());
        Assert.assertEquals("Hello World!", output.getOutput().trim());
    }

    @Test
    public void multipleJavaFilesTest(){
        Output output = compileAndRun(multipleFilesCompileParameters.getSourceCodes().get(0), multipleFilesCompileParameters, multipleFilesRunParameters);
        System.out.println(output.getError());
        String expected = "Hello from main\nHello from A";
        Assert.assertEquals(expected, output.getOutput().trim());
    }

    //@Ignore("Gradle hangs when a test accesses the security manager: https://github.com/gradle/gradle/issues/3526")
    @Test
    public void writeTest() {
        try {
            compiler.getSecurityManager().checkWrite(runOutputPath);
            Output output = compileAndRun(fileWriterCompileParameters.getSourceCodes().get(0), fileWriterCompileParameters, fileWriterRunParameters);
            System.out.println(output.toString());
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

    public Output compileAndRun(SourceFile mainSource, CompilerParameters compilerParameters, RunParameters runParameters) {
        try {
            Output compileOutput = compiler.compile(compilerParameters);
            if (compileOutput.getExitValue() == 0) {
                return compiler.run(mainSource, compilerParameters, runParameters);
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
