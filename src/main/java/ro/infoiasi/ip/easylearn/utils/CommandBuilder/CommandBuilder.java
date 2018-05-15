package ro.infoiasi.ip.easylearn.utils.CommandBuilder;

import ro.infoiasi.ip.easylearn.compiler.SourceFile;

import java.util.List;

public interface CommandBuilder {
    String buildCompileCommand(List<SourceFile> sources, String rootDirectoryPath);

    String buildRunCommand(String rootDirectoryPath, SourceFile mainSource);
}
