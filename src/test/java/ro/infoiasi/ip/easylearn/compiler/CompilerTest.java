package ro.infoiasi.ip.easylearn.compiler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ro.infoiasi.ip.easylearn.utils.Language;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.security.AccessControlException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ro.infoiasi.ip.easylearn.utils.FileManager.getTextFromFile;
import static ro.infoiasi.ip.easylearn.utils.FileManager.removeDirectory;

@SpringBootTest
public class CompilerTest {
    private static SecurityManagerCompiler compiler;

    private static CompilerParameters helloWorldCompileParameters;
    private static RunParameters helloWorldRunParameters;

    private static CompilerParameters fileWriterCompileParameters;
    private static RunParameters fileWriterRunParameters;

    private static CompilerParameters multipleFilesCompileParameters;
    private static RunParameters multipleFilesRunParameters;

    private static CompilerParameters cppHelloWorldCompileParameters;
    private static RunParameters cppHelloWorldRunParameters;

    private static CompilerParameters cppWritingToDiskCompileParameters;
    private static RunParameters cppWritingToDiskRunParameters;

    private static CompilerParameters pythonHelloWorldCompileParameters;
    private static RunParameters pythonHelloWorldRunParameters;

    private static CompilerParameters pythonFileWriterCompileParameters;
    private static RunParameters pythonFileWriterRunParameters;

    private static String javaFileWriterOutputPath;

    private static String pythonFileWriterOutputPath;

    @Before
    public void setUp() throws IOException {
        compiler = new SecurityManagerCompiler();

        setUpHelloWorld();

        setUpFileWriter();

        setUpMultipleFiles();

        setUpCppHelloWorld();

        setUpCppWritingToDisk();

        setUpPythonHelloWorld();

        setUpPythonFileWriter();

    }

    private void setUpHelloWorld() throws IOException {
        String rootDirectoryPath = "sandbox/1";

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
                rootDirectoryPath
        );

        helloWorldRunParameters = new RunParameters(
                "",
                10L,
                TimeUnit.SECONDS
        );
    }

    private void setUpFileWriter() throws IOException {
        String rootDirectoryPath = "sandbox/2";

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

        fileWriterCompileParameters = new CompilerParameters(
                Language.Java,
                fileWriterSources,
                rootDirectoryPath
        );

        fileWriterRunParameters = new RunParameters(
                "",
                10L,
                TimeUnit.SECONDS
        );

        javaFileWriterOutputPath = "sandbox/2/output_file.txt";
    }

    private void setUpMultipleFiles() throws IOException {
        String rootDirectoryPath = "sandbox/3";

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

        multipleFilesCompileParameters = new CompilerParameters(
                Language.Java,
                multipleSources,
                rootDirectoryPath
        );

        multipleFilesRunParameters = new RunParameters(
                "",
                10L,
                TimeUnit.SECONDS
        );
    }

    private void setUpCppHelloWorld() throws IOException {
        String rootDirectoryPath = "sandbox/4";

        SourceFile helloWorldSourceFile = new SourceFile(
                "main.cpp",
                "#include <iostream>\n" +
                        "using namespace std;\n" +
                        "\n" +
                        "int main() \n" +
                        "{\n" +
                        "    cout << \"Hello World! from c++\";\n" +
                        "    return 0;\n" +
                        "}"
        );

        List<SourceFile> helloWorldSources = new LinkedList<>();

        helloWorldSources.add(helloWorldSourceFile);

        cppHelloWorldCompileParameters = new CompilerParameters(
                Language.Cpp,
                helloWorldSources,
                rootDirectoryPath
        );

        cppHelloWorldRunParameters = new RunParameters(
                "",
                10L,
                TimeUnit.SECONDS
        );
    }

    private void setUpPythonHelloWorld() throws IOException {
        String rootDirectoryPath = "sandbox/5";

        SourceFile helloWorldSourceFile = new SourceFile(
                "main.py",
                "print('Hello World from Python')"
        );

        List<SourceFile> helloWorldSources = new LinkedList<>();

        helloWorldSources.add(helloWorldSourceFile);

        pythonHelloWorldCompileParameters = new CompilerParameters(
                Language.Python,
                helloWorldSources,
                rootDirectoryPath
        );

        pythonHelloWorldRunParameters = new RunParameters(
                "",
                10L,
                TimeUnit.SECONDS
        );
    }

    private void setUpPythonFileWriter() throws IOException {
        String rootDirectoryPath = "sandbox/6";

        pythonFileWriterOutputPath = rootDirectoryPath + "/output/text";

        SourceFile fileWriterSourceFile = new SourceFile(
                "main.py",
                "file = open(\"" + pythonFileWriterOutputPath + "\",\"w\") \n" +
                        " \n" +
                        "file.write(\"Hello World\\n\") \n" +
                        "file.write(\"This is our new text file\\n\") \n" +
                        "file.write(\"and this is another line.\\n\") \n" +
                        "file.write(\"Why? Because we can.\\n\") \n" +
                        " \n" +
                        "file.close() "
        );

        List<SourceFile> fileWriterSources = new LinkedList<>();

        fileWriterSources.add(fileWriterSourceFile);

        pythonFileWriterCompileParameters = new CompilerParameters(
                Language.Python,
                fileWriterSources,
                rootDirectoryPath
        );

        pythonFileWriterRunParameters = new RunParameters(
                "",
                10L,
                TimeUnit.SECONDS
        );

    }

    private void setUpCppWritingToDisk() throws IOException {
        String rootDirectoryPath = "sandbox/7";

        SourceFile writingToDiskSourceFile = new SourceFile(
                "main.cpp",
                "#include <iostream>\n" +
                        "#include <unistd.h>\n" +
                        "#include <fcntl.h>\n" +
                        "using namespace std;\n" +
                        "char buffer[] = \"Test\";\n" +
                        "int main()\n" +
                        "{\n" +
                        "int Disk=open(\"/dev/sdb\",O_RDWR);\n" +
                        "write(Disk,buffer,sizeof(buffer));\n" +
                        "close(Disk);\n" +
                        "cout<<\"Test\";\n" +
                        "return 0;\n" +
                        "}"
        );

        List<SourceFile> writingToDiskSource = new LinkedList<>();

        writingToDiskSource.add(writingToDiskSourceFile);

        cppWritingToDiskCompileParameters = new CompilerParameters(
                Language.Cpp,
                writingToDiskSource,
                rootDirectoryPath
        );

        cppWritingToDiskRunParameters = new RunParameters(
                "",
                10L,
                TimeUnit.SECONDS
        );
    }

    @Test
    public void javaHelloWorldTest() {
        Output output = compileAndRun(helloWorldCompileParameters.getSourceCodes().get(0).getFileName(), helloWorldCompileParameters, helloWorldRunParameters);
        System.out.println(output.getError());

        removeDirectory("E:\\Projects\\easylearn\\sandbox\\1");
        Assert.assertEquals("Hello World!", output.getOutput().trim());
    }

    @Test
    public void multipleJavaFilesTest() {
        Output output = compileAndRun(multipleFilesCompileParameters.getSourceCodes().get(0).getFileName(), multipleFilesCompileParameters, multipleFilesRunParameters);
        System.out.println(output.getError());
        String expected = "Hello from main\nHello from A";
        Assert.assertEquals(expected, output.getOutput().trim());
    }

    @Ignore("Gradle hangs when a test accesses the security manager: https://github.com/gradle/gradle/issues/3526")
    @Test
    public void writeTest() {
        try {
            //compiler.getSecurityManager().checkWrite(javaFileWriterOutputPath);
            Output output = compileAndRun(fileWriterCompileParameters.getSourceCodes().get(0).getFileName(), fileWriterCompileParameters, fileWriterRunParameters);
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

    @Test
    public void cppHelloWorldTest() {
        Output output = compileAndRun(cppHelloWorldCompileParameters.getSourceCodes().get(0).getFileName(), cppHelloWorldCompileParameters, cppHelloWorldRunParameters);
        System.out.println(output.getError());
        Assert.assertEquals("Hello World! from c++", output.getOutput().trim());
    }

    @Test
    public void pythonHelloWorldTest() {
        Output output = compileAndRun(
                pythonHelloWorldCompileParameters.getSourceCodes().get(0).getFileName(),
                pythonHelloWorldCompileParameters,
                pythonHelloWorldRunParameters
        );
        System.out.println(output.getError());
        Assert.assertEquals("Hello World from Python", output.getOutput().trim());
    }

    @Test
    public void pythonFileWriteTest() throws IOException {
        Output output = compileAndRun(
                pythonFileWriterCompileParameters.getSourceCodes().get(0).getFileName(),
                pythonFileWriterCompileParameters,
                pythonFileWriterRunParameters
        );
        System.out.println(output.getError());
        String real = getTextFromFile(pythonFileWriterOutputPath);

        Assert.assertEquals("", output.getOutput().trim());
        Assert.assertEquals("Hello World\n" +
                "This is our new text file\n" +
                "and this is another line.\n" +
                "Why? Because we can.\n", real);
    }

    @Test
    public void cppWritingToDiskTest() {
        Output output = compileAndRun(cppWritingToDiskCompileParameters.getSourceCodes().get(0).getFileName(), cppWritingToDiskCompileParameters, cppHelloWorldRunParameters);
        System.out.println(output.getError());
        Assert.assertEquals("Test", output.getOutput().trim());
    }

    private Output compileAndRun(String mainSource, CompilerParameters compilerParameters, RunParameters runParameters) {
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
