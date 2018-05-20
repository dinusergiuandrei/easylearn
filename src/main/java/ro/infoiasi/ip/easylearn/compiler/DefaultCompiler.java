package ro.infoiasi.ip.easylearn.compiler;

import static ro.infoiasi.ip.easylearn.utils.FileManager.addSourcesToDirectory;
import static ro.infoiasi.ip.easylearn.utils.FileManager.createDirectory;
import static ro.infoiasi.ip.easylearn.utils.FileManager.getFilePathSeparator;
import static ro.infoiasi.ip.easylearn.utils.ProcessManager.addKeyboardInput;
import static ro.infoiasi.ip.easylearn.utils.ProcessManager.getProcessOutput;
import static ro.infoiasi.ip.easylearn.utils.ProcessManager.runCommand;

/**
 * This type of compiler will take no regard towards security.
 */
public class DefaultCompiler extends Compiler{

    @Override
    public Output compile(CompilerParameters parameters) throws Exception {

        createDirectory(parameters.getRootDirectoryPath());

        addSourcesToDirectory(parameters.getSourceCodes(), parameters.getRootDirectoryPath());

        String command = parameters
                .getLanguage()
                .getCommandBuilder()
                .buildCompileCommand(
                        parameters.getSourceCodes(),
                        parameters.getRootDirectoryPath());

        if (command == null || command.length() == 0) {
            return Output.getSuccessOutput();
        }
        Process process = runCommand(command);

        return getProcessOutput(process);
    }

    @Override
    public Output run(String mainSource, CompilerParameters compilerParameters, RunParameters runParameters) throws Exception {

        String command = compilerParameters
                .getLanguage()
                .getCommandBuilder()
                .buildRunCommand(
                        compilerParameters.getRootDirectoryPath(),
                        mainSource
                );

        Process process = runCommand(command);

        addKeyboardInput(process, runParameters.getKeyboardInput());

        Output output = getProcessOutput(process, runParameters.getTimeout(), runParameters.getTimeUnit());

        return output;
    }

    @Override
    public void setUpSecurity() {
    }
}
