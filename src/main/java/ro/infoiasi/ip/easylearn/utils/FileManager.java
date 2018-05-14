package ro.infoiasi.ip.easylearn.utils;

import ro.infoiasi.ip.easylearn.compiler.SourceFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collection;

public abstract class FileManager {
    public static String getCurrentWorkingDirectory(){
        return System.getProperty("user.dir");
    }

    public static String getFilePathSeparator(){
        return System.getProperty("file.separator");
    }

    public static Boolean createDirectory(String directoryPath){
        File directory = new File(directoryPath);
        if(!directory.exists())
            return new File(directoryPath).mkdir();
        return false;
    }

    public static void addSourcesToFile(Collection<SourceFile> sources, String rootDirectory){
        for (SourceFile sourceFile : sources) {
            addSourceToFile(sourceFile.getContent(), rootDirectory + getFilePathSeparator() + sourceFile.getTitle());
        }
    }

    public static void addSourceToFile(String source, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            writer.write(source);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
