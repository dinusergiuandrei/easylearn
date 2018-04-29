package ro.infoiasi.ip.easylearn.compiler;


import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

class FileWriter {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("sandbox/sample/run_output/java/output_file.txt");

        CompilerParameters compilerParameters =
                new CompilerParameters("sandbox/sample/sources/java/HelloWorld.java", "sandbox/sample/generated/java/", "", "", 1000L, MILLISECONDS);

        Compiler compiler = new Compiler();
        Output output = compiler.compileAndRun(compilerParameters);

        writer.println(output);
        writer.flush();
        writer.close();
    }
}