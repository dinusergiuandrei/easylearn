package ro.infoiasi.ip.easylearn.utils;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Strings {

    public static String extractClassNameFromSourcePath(String sourcePath) {
        if (sourcePath.endsWith(".java")) {
            String classPath = sourcePath.substring(0, sourcePath.length() - 5);

            classPath = FilenameUtils.separatorsToSystem(classPath);

            classPath = classPath.replace('\\', '/');
            String[] nodes = classPath.split("/");
            return nodes[nodes.length - 1];
        } else {
            System.err.println("Source path does not point to a java file: " + sourcePath);
        }
        return null;
    }

    public static String getStringFromInputStream(InputStream ins) throws Exception {
        StringBuilder s = new StringBuilder();
        String line;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            s.append(line);
            s.append('\n');
        }
        return s.toString();
    }
}
