package ro.infoiasi.ip.easylearn.compiler;

import ro.infoiasi.ip.easylearn.utils.Language;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static ro.infoiasi.ip.easylearn.utils.FileManager.*;
import static ro.infoiasi.ip.easylearn.utils.ProcessManager.*;

/**
 * The compile output will pe added in rootDirectory/compile_path
 * The run will call a program that will load a security manager and run the
 * submission code.
 */

public class SecurityManagerCompiler extends Compiler {
    private SecurityManager securityManager;

    private String sourceDirectoryName = "sources";

    private String sourceDirectoryAbsolutePath;

    @Override
    public Output compile(CompilerParameters parameters) throws Exception {

        sourceDirectoryAbsolutePath = parameters.getRootDirectoryPath() + getFilePathSeparator() + sourceDirectoryName;

        createDirectory(parameters.getRootDirectoryPath());
        createDirectory(sourceDirectoryAbsolutePath);

        addSourcesToDirectory(parameters.getSourceCodes(), sourceDirectoryAbsolutePath);

        String command = parameters
                .getLanguage()
                .getCommandBuilder()
                .buildCompileCommand(
                        parameters.getSourceCodes(),
                        sourceDirectoryAbsolutePath);

        if (command == null || command.length() == 0) {
            return Output.getSuccessOutput();
        }
        Process process = runCommand(command);

        return getProcessOutput(process);
    }

    @Override
    public Output run(String mainSource, CompilerParameters compilerParameters, RunParameters runParameters) throws Exception {
        Output output;

        //output = runWithProcessCall(mainSource, compilerParameters, runParameters);

        output = runWithIntermediateProgram(mainSource, compilerParameters, runParameters);

        return output;
    }

    private Output runWithProcessCall(String mainSource, CompilerParameters compilerParameters, RunParameters runParameters) throws Exception{
        String command = compilerParameters
                .getLanguage()
                .getCommandBuilder()
                .buildRunCommand(
                        sourceDirectoryAbsolutePath,
                        mainSource
                );
        Process process = runCommand(command);

        addKeyboardInput(process, runParameters.getKeyboardInput());

        Output output = getProcessOutput(process, runParameters.getTimeout(), runParameters.getTimeUnit());

        return output;
    }

    private Output runWithIntermediateProgram(String mainSource, CompilerParameters compilerParameters, RunParameters runParameters) throws Exception {
        String command = compilerParameters
                .getLanguage()
                .getCommandBuilder()
                .buildRunCommand(
                        sourceDirectoryAbsolutePath,
                        mainSource
                );

        String cleanCommand = command.replace("\\", "/"); // "start " + command.replace(...) ?

        String outputDirectoryPath = compilerParameters.getRootDirectoryPath()+getFilePathSeparator()+"output";

        createDirectory(outputDirectoryPath);

        List<SourceFile> containerSourceFiles = new LinkedList<>();

        String mainContainerProgramString = buildMainContainerProgramString(
                cleanCommand, runParameters.getKeyboardInput(),
                runParameters.getTimeout(), runParameters.getTimeUnit(),
                compilerParameters.getRootDirectoryPath() + "/output"
        );

        containerSourceFiles.add(new SourceFile ("Main.java", mainContainerProgramString));
        containerSourceFiles.add(new SourceFile("Output.java", buildOutputFileString()));
        containerSourceFiles.add(new SourceFile("SourceFile.java", buildSourceFileString()));
        containerSourceFiles.add(new SourceFile("FileManager.java", buildFileManagerString()));
        containerSourceFiles.add(new SourceFile("ProcessManager.java", buildProcessManagerString()));

        Compiler defaultCompiler = new DefaultCompiler();

        CompilerParameters containerCompilerParameters = new CompilerParameters(
                Language.Java,
                containerSourceFiles,
                compilerParameters.getRootDirectoryPath()
        );

        RunParameters containerRunParameters = new RunParameters(
                "",
                runParameters.getTimeout(),
                runParameters.getTimeUnit()
        );

        Output defaultCompilerOutput = defaultCompiler.compile(containerCompilerParameters);

        if(defaultCompilerOutput.getExitValue()!=0){
            System.out.println(defaultCompilerOutput.getError());
        }

        defaultCompiler.run("Main.java", containerCompilerParameters, containerRunParameters);

        return collectOutput(outputDirectoryPath);
    }

    private Output collectOutput(String outputDirectoryPath) throws IOException {
        Output output = new Output();

        String successPath = outputDirectoryPath + getFilePathSeparator() + "output";
        String errorPath = outputDirectoryPath + getFilePathSeparator() + "error";

        String outputString = getTextFromFile(successPath);
        String errorString = getTextFromFile(errorPath);

        output.setOutput(outputString);
        output.setError(errorString);

        return output;
    }

    @Override
    public void setUpSecurity() {
        loadSecurityManager();
    }

    private void loadSecurityManager() {
        SecurityManager securityManager = new SecurityManager();
        System.setSecurityManager(securityManager);
        this.securityManager = System.getSecurityManager();
    }

    public void discardSecurityManager() {
        this.securityManager = null;
        System.setSecurityManager(this.securityManager);
    }

    SecurityManager getSecurityManager() {
        if (this.securityManager == null) {
            this.loadSecurityManager();
        }

        return securityManager;
    }

    private String buildMainContainerProgramString(String command, String input, Long timeout, TimeUnit timeUnit, String outputPath) {
        return "import java.io.BufferedWriter;\n"
                + "import java.io.IOException;\n"
                + "import java.io.OutputStreamWriter;\n"
                + "import java.util.concurrent.TimeUnit;\n"
                + "import java.io.PrintWriter;\n"
                + "import java.util.concurrent.TimeUnit;\n\n"
                + "public class Main {\n"
                + "   public static void main(String args[]) throws Exception {\n"
                + "     //SecurityManager securityManager = new SecurityManager();"
                + "     //securityManager.checkExec(\"<<ALL FILES>>\");\n"
                + "     //System.setSecurityManager(new SecurityManager());\n"
                + "     Process process = Runtime.getRuntime().exec(\"" + command + "\");\n"
                + "     ProcessManager.addKeyboardInput(process, \"" + input + "\");\n"
                + "     Output output = ProcessManager.getProcessOutput(process, " + timeout + "L,  TimeUnit.MILLISECONDS );\n"
                + "     PrintWriter writer = new PrintWriter(\"" + outputPath+ "/error" + "\", \"UTF-8\");\n"
                + "     writer.println(output.getError());\n"
                + "     writer.close();\n"
                + "     writer = new PrintWriter(\"" + outputPath+ "/output" + "\", \"UTF-8\");\n"
                + "     writer.println(output.getOutput());\n"
                + "     writer.close();\n"
                + "     //System.setSecurityManager(null);\n"
                + "   }\n"
                + "}\n";
    }

    private String buildOutputFileString(){
        return "public class Output {\n" +
                "    private String error;\n" +
                "\n" +
                "    private String output;\n" +
                "\n" +
                "    private Integer exitValue;\n" +
                "\n" +
                "    public static Output getSuccessOutput(){\n" +
                "        Output output  = new Output();\n" +
                "        output.setExitValue(0);\n" +
                "        return output;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public String toString() {\n" +
                "        StringBuilder stringBuilder = new StringBuilder();\n" +
                "        if (this.output != null && this.output.length() > 0) {\n" +
                "            stringBuilder.append(this.output);\n" +
                "        }\n" +
                "        if (this.error != null && this.error.length() > 0) {\n" +
                "            stringBuilder.append(\"Run error: \\n\").append(this.error);\n" +
                "        }\n" +
                "        stringBuilder.append(\"Exit value: \").append(this.exitValue);\n" +
                "\n" +
                "        return stringBuilder.toString();\n" +
                "    }\n" +
                "\n" +
                "    public Integer getExitValue() {\n" +
                "        return exitValue;\n" +
                "    }\n" +
                "\n" +
                "    public void setExitValue(Integer exitValue) {\n" +
                "        this.exitValue = exitValue;\n" +
                "    }\n" +
                "\n" +
                "    public String getError() {\n" +
                "        return error;\n" +
                "    }\n" +
                "\n" +
                "    public void setError(String error) {\n" +
                "        this.error = error;\n" +
                "    }\n" +
                "\n" +
                "    public String getOutput() {\n" +
                "        return output;\n" +
                "    }\n" +
                "\n" +
                "    public void setOutput(String output) {\n" +
                "        this.output = output;\n" +
                "    }\n" +
                "}";
    }

    private String buildSourceFileString(){
        return "\n" +
                "public class SourceFile {\n" +
                "    private String title;\n" +
                "\n" +
                "    private String content;\n" +
                "\n" +
                "    public SourceFile(String title, String content) {\n" +
                "        this.title = title;\n" +
                "        this.content = content;\n" +
                "    }\n" +
                "\n" +
                "    public String getTitle() {\n" +
                "        return title;\n" +
                "    }\n" +
                "\n" +
                "    public String getContent() {\n" +
                "        return content;\n" +
                "    }\n" +
                "\n" +
                "    public void setTitle(String title) {\n" +
                "        this.title = title;\n" +
                "    }\n" +
                "\n" +
                "    public void setContent(String content) {\n" +
                "        this.content = content;\n" +
                "    }\n" +
                "}";
    }

    private String buildFileManagerString(){
        return "\n" +
                "import java.io.File;\n" +
                "import java.io.FileNotFoundException;\n" +
                "import java.io.PrintWriter;\n" +
                "import java.util.Collection;\n" +
                "\n" +
                "public abstract class FileManager {\n" +
                "    public static String getCurrentWorkingDirectory(){\n" +
                "        return System.getProperty(\"user.dir\");\n" +
                "    }\n" +
                "\n" +
                "    public static String getFilePathSeparator(){\n" +
                "        return \"/\";\n" +
                "        //return System.getProperty(\"file.separator\");\n" +
                "    }\n" +
                "\n" +
                "    public static Boolean createDirectory(String directoryPath){\n" +
                "        File directory = new File(directoryPath);\n" +
                "        if(!directory.exists())\n" +
                "            return new File(directoryPath).mkdir();\n" +
                "        return false;\n" +
                "    }\n" +
                "\n" +
                "    public static void addSourcesToDirectory(Collection<SourceFile> sources, String rootDirectory){\n" +
                "        for (SourceFile sourceFile : sources) {\n" +
                "            addSourceToFile(sourceFile.getContent(), rootDirectory + getFilePathSeparator() + sourceFile.getTitle());\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public static void addSourceToFile(String source, String filename) {\n" +
                "        try {\n" +
                "            PrintWriter writer = new PrintWriter(filename);\n" +
                "            writer.write(source);\n" +
                "            writer.flush();\n" +
                "            writer.close();\n" +
                "        } catch (FileNotFoundException e) {\n" +
                "            System.out.println(e.getMessage());\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }

    private String buildProcessManagerString(){
        return "\n" +
                "import java.io.BufferedWriter;\n" +
                "import java.io.IOException;\n" +
                "import java.io.OutputStreamWriter;\n" +
                "import java.util.concurrent.TimeUnit;\n" +
                "import java.io.BufferedReader;\n" +
                "import java.io.InputStream;\n" +
                "import java.io.InputStreamReader;" +
                "\n" +
                "\n" +
                "public abstract class ProcessManager {\n" +
                "\n" +
                "    public static Process runCommand(String command) throws IOException {\n" +
                "        return runCommandRuntime(command);\n" +
                "    }\n" +
                "\n" +
                "    private static Process runCommandRuntime(String command) throws IOException {\n" +
                "        return Runtime.getRuntime().exec(command);\n" +
                "    }\n" +
                "\n" +
                "    public static String removePathEnd(String path){\n" +
                "        return path.substring(0, path.indexOf('.'));\n" +
                "    }\n" +
                "\n" +
                "    public static void addKeyboardInput(Process process, String input) throws IOException {\n" +
                "        if (input != null && input.length() > 0) {\n" +
                "            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));\n" +
                "            writer.write(input);\n" +
                "            writer.flush();\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    public static Output getProcessOutput(Process process) throws Exception {\n" +
                "        process.waitFor();\n" +
                "        return getProcessRunOutput(process);\n" +
                "    }\n" +
                "\n" +
                "    public static Output getProcessOutput(Process process, Long timeout, TimeUnit timeUnit) throws Exception {\n" +
                "        process.waitFor(timeout, timeUnit);\n" +
                "        return getProcessRunOutput(process);\n" +
                "    }\n" +
                "\n" +
                "    private static Output getProcessRunOutput(Process process) throws Exception {\n" +
                "        Output runOutput = new Output();\n" +
                "        runOutput.setError(getStringFromInputStream(process.getErrorStream()));\n" +
                "        runOutput.setOutput(getStringFromInputStream(process.getInputStream()));\n" +
                "        runOutput.setExitValue(process.exitValue());\n" +
                "\n" +
                "        return runOutput;\n" +
                "    }\n" +
                "public static String getStringFromInputStream(InputStream ins) throws Exception {\n" +
                "        StringBuilder s = new StringBuilder();\n" +
                "        String line;\n" +
                "        BufferedReader in = new BufferedReader(\n" +
                "                new InputStreamReader(ins));\n" +
                "        while ((line = in.readLine()) != null) {\n" +
                "            s.append(line);\n" +
                "            s.append('\\n');\n" +
                "        }\n" +
                "        return s.toString();\n" +
                "    }" +
                "}";
    }

    private void addMainProgramToFile(String file, String fileName, String rootDirectory){
        List<SourceFile> sourceFiles = new LinkedList<>();
        sourceFiles.add(new SourceFile(fileName, file));
        addSourcesToDirectory(sourceFiles, rootDirectory);
    }
}
