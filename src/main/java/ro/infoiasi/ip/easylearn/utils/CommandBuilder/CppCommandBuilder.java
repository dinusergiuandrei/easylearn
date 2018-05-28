package ro.infoiasi.ip.easylearn.utils.CommandBuilder;

import ro.infoiasi.ip.easylearn.compiler.SourceFile;

import java.util.List;

import static ro.infoiasi.ip.easylearn.utils.FileManager.getFilePathSeparator;
import static ro.infoiasi.ip.easylearn.utils.ProcessManager.removePathEnd;

public class CppCommandBuilder extends CommandBuilder {
    @Override
    public String buildCompileCommand(List<SourceFile> sources, String rootDirectoryPath) {
        StringBuilder commandBuilder = new StringBuilder();

        for (int sourceIndex = 0; sourceIndex < sources.size() - 1; ++sourceIndex) {
            SourceFile sourceFile = sources.get(sourceIndex);

            commandBuilder
                    .append(getCppCompileCommandSingleSource(sourceFile, rootDirectoryPath))
                    .append(" & ");
        }

        SourceFile lastSourceFile = sources.get(sources.size() - 1);

        commandBuilder.append(getCppCompileCommandSingleSource(lastSourceFile, rootDirectoryPath));

        return commandBuilder.toString();
    }

    @Override
    public String buildRunCommand(String rootDirectoryPath, String mainSource) {
        String cppTitle = removePathEnd(mainSource);
        return rootDirectoryPath + getFilePathSeparator() + cppTitle + ".exe";
    }


    private static String getCppCompileCommandSingleSource(SourceFile sourceFile, String rootDirectory){
        StringBuilder commandBuilder = new StringBuilder();
        String cppTitle = removePathEnd(sourceFile.getFileName());
        commandBuilder
                .append("g++ ")
                .append(rootDirectory)
                .append(getFilePathSeparator())
                .append(sourceFile.getFileName())
                .append(" -o ")
                .append(rootDirectory)
                .append(getFilePathSeparator())
                .append(cppTitle)
                .append(".exe ");
        return commandBuilder.toString();
    }
}
