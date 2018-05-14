package ro.infoiasi.ip.easylearn.compiler;

import ro.infoiasi.ip.easylearn.utils.Language;

import java.io.File;


public class SecurityManagerCompiler extends Compiler {
    private SecurityManager securityManager;

    public Output compile(CompilerParameters parameters) throws Exception {
        String command = null;

        Language language = parameters.getLanguage();

        if(!new File(parameters.getCompileOutputPath()).exists()){
            this.setUpRootDirectory(parameters.getCompileOutputPath());
        }

        for (SourceFile sourceFile : parameters.getSourceCodes()) {
            addSourceToFile(sourceFile.getContent(), parameters.getCompileOutputPath() + System.getProperty("file.separator") + sourceFile.getTitle());
        }

        switch (language) {
            case Java:
                command = "javac " + parameters.getCompileOutputPath() + System.getProperty("file.separator") + "*.java";
                break;
        }

        Process process = Runtime.getRuntime().exec(command);

        return getProcessOutput(process);
    }

    public Output run(SourceFile mainClass, CompilerParameters compilerParameters, RunParameters runParameters) throws Exception {

        Language language = compilerParameters.getLanguage();

        String command = null;

        switch (language) {
            case Java:
                String title = null;
                if(mainClass.getTitle().endsWith(".java")){
                    title = mainClass.getTitle().substring(0, mainClass.getTitle().length() - 5);
                }
                command = "java -cp " + compilerParameters.getCompileOutputPath() + " " + title;
                break;
        }

        Process process = Runtime.getRuntime().exec(command);

        addKeyboardInput(process, runParameters.getKeyboardInput());

        return getProcessOutput(process, runParameters.getTimeout(), runParameters.getTimeUnit());
    }

    public void loadSecurityManager() {
        SecurityManager securityManager = new SecurityManager();
        System.setSecurityManager(securityManager);
        this.securityManager = System.getSecurityManager();
    }

    public SecurityManager getSecurityManager() {
        if (this.securityManager == null) {
            this.loadSecurityManager();
        }
        return securityManager;
    }

}
