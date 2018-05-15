package ro.infoiasi.ip.easylearn.compiler;

import ro.infoiasi.ip.easylearn.utils.CommandBuilder.CommandBuilder;
import ro.infoiasi.ip.easylearn.utils.Language;

import static ro.infoiasi.ip.easylearn.utils.FileManager.*;
import static ro.infoiasi.ip.easylearn.utils.ProcessManager.*;

public class SecurityManagerCompiler extends Compiler {
    private SecurityManager securityManager;

    @Override
    public Output compile(CompilerParameters parameters) throws Exception {

        createDirectory(parameters.getRootDirectoryPath());

        addSourcesToFile(parameters.getSourceCodes(), parameters.getRootDirectoryPath());

        String command = parameters
                .getLanguage()
                .getCommandBuilder()
                .buildCompileCommand(
                        parameters.getSourceCodes(),
                        parameters.getRootDirectoryPath());

        if (command == null || command.length() == 0) {
            return Output.getSuccessOutput();
        }
        Process process = runCommand(getCurrentWorkingDirectory(), command);

        return getProcessOutput(process);
    }

    @Override
    public Output run(SourceFile mainSource, CompilerParameters compilerParameters, RunParameters runParameters) throws Exception {

        String command = compilerParameters
                .getLanguage()
                .getCommandBuilder()
                .buildRunCommand(
                        compilerParameters.getRootDirectoryPath(),
                        mainSource
                );

        Process process = runCommand(getCurrentWorkingDirectory(), command);

        addKeyboardInput(process, runParameters.getKeyboardInput());

        return getProcessOutput(process, runParameters.getTimeout(), runParameters.getTimeUnit());
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

    SecurityManager getSecurityManager() {
        if (this.securityManager == null) {
            this.loadSecurityManager();
        }
        return securityManager;
    }

}
