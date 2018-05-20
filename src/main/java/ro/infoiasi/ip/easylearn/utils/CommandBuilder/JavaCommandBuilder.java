package ro.infoiasi.ip.easylearn.utils.CommandBuilder;

import ro.infoiasi.ip.easylearn.compiler.SourceFile;

import java.util.List;

import static ro.infoiasi.ip.easylearn.utils.FileManager.getFilePathSeparator;
import static ro.infoiasi.ip.easylearn.utils.ProcessManager.removePathEnd;

public class JavaCommandBuilder implements CommandBuilder {
    @Override
    public String buildCompileCommand(List<SourceFile> sources, String rootDirectoryPath) {
        return "javac " + rootDirectoryPath + getFilePathSeparator() + "*.java";
    }

    @Override
    public String buildRunCommand(String rootDirectoryPath, String mainSource) {
        String javaTitle = removePathEnd(mainSource);
        return "java -cp " + rootDirectoryPath + " " + javaTitle;
    }
}
