package ro.infoiasi.ip.easylearn.utils.CommandBuilder;

import ro.infoiasi.ip.easylearn.compiler.SourceFile;

import java.util.List;

public abstract class CommandBuilder {
    abstract public String buildCompileCommand(List<SourceFile> sources, String rootDirectoryPath);

    abstract public String buildRunCommand(String rootDirectoryPath, String mainSource);

    public String buildRunCommand(String rootDirectoryPath, String mainSource, String policyFilePath){
        return "";
    };
}
