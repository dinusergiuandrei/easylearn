import java.io.*;

class FileWriter {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("../../sample/run_output/java/output_file.txt");
        writer.println("The first line");
        writer.close();
    }
}