package ro.infoiasi.ip.easylearn.utils;

import ro.infoiasi.ip.easylearn.compiler.SourceFile;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Collection;

public abstract class FileManager {
    public static String getCurrentWorkingDirectory(){
        return System.getProperty("user.dir");
    }

    public static String getFilePathSeparator(){
        return "/";
        //return System.getProperty("file.separator");
    }

    public static Boolean createDirectory(String directoryPath){
        File directory = new File(directoryPath);
        if(!directory.exists())
            return new File(directoryPath).mkdir();
        return false;
    }

    public static Boolean createFile(String filePath) throws IOException {

        File f = new File(filePath);

        f.getParentFile().mkdirs();
        return f.createNewFile();
    }

    public static void addSourcesToDirectory(Collection<SourceFile> sources, String rootDirectory){
        for (SourceFile sourceFile : sources) {
            writeTextToFile(sourceFile.getContent(), rootDirectory + getFilePathSeparator() + sourceFile.getFileName());
        }
    }

    public static void writeTextToFile(String source, String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            writer.write(source);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getTextFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = reader.readLine();
            }
            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

    public static void removeDirectory(String directoryPath)  {
        File file = new File(directoryPath);
        if(!file.exists())
            return;
        if(file.isFile()){
            if(!file.delete()){
                System.err.println("Could not delete file: " + file.getPath());
            }
        }
        else {
            File[] files = file.listFiles();
            for (File childFile : files) {
                removeDirectory(childFile.getAbsolutePath());
            }
            if(!file.delete()){
                System.err.println("Could not delete directory: " + file.getPath());
            }
        }
    }
}
