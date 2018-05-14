package ro.infoiasi.ip.easylearn.compiler;

import ro.infoiasi.ip.easylearn.utils.Language;

import static ro.infoiasi.ip.easylearn.utils.FileManager.*;
import static ro.infoiasi.ip.easylearn.utils.ProcessManager.*;

public class SecurityManagerCompiler extends Compiler {
    private SecurityManager securityManager;

    @Override
    public Output compile(CompilerParameters parameters) throws Exception {
        String command = null;

        createDirectory(parameters.getRootDirectoryPath());

        addSourcesToFile(parameters.getSourceCodes(), parameters.getRootDirectoryPath());

        switch (parameters.getLanguage()) {
            case Java:
                command = getJavaCompileCommand(parameters.getRootDirectoryPath());
                break;
            case Cpp:
                command = getCppCompileCommandMultipleSources(parameters.getSourceCodes(), parameters.getRootDirectoryPath());
                break;
        }

        Process process = runCommand(getCurrentWorkingDirectory(), command);

        return getProcessOutput(process);
    }

    @Override
    public Output run(SourceFile mainSource, CompilerParameters compilerParameters, RunParameters runParameters) throws Exception {

        String command = null;

        Language language = compilerParameters.getLanguage();

        switch (language) {
            case Java:
                command = getJavaRunCommand(compilerParameters.getRootDirectoryPath(), mainSource);
                break;
            case Cpp:
                command = getCppRunCommand(compilerParameters.getRootDirectoryPath(), mainSource);
                break;
        }

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
