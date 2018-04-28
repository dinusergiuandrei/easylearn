package ro.infoiasi.ip.easylearn.Compiler;

import org.junit.Before;
import org.junit.Test;
import ro.infoiasi.ip.easylearn.compiler.Compiler;

/**
 * Do not test. Compiler behaviour is not yet resolved.
 */
public class CompilerTest {
    public static String helloWorldSourcePath = "sample/sources/java/HelloWorld.java";
    //public static String helloWorldOutputPath = "sample/run_output/java/output_file.txt";

    public static String fileWriterSourcePath = "sample/sources/java/FileWriter.java";
    public static String fileWriterOutputPath = "sample/run_output/java/output_file.txt";


    Compiler compiler;

    //@Before
    public void run(){
        this.compiler = new Compiler();
        //compiler.loadSecurityManager();
    }

    //@Test
    public void runTest(){
        compiler.compileAndRun(helloWorldSourcePath);
    }
}
