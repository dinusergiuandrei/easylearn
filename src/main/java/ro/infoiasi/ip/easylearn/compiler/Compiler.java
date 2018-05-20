package ro.infoiasi.ip.easylearn.compiler;

public abstract class Compiler {

    public abstract Output compile(CompilerParameters parameters) throws Exception;

    public abstract Output run(String mainClass, CompilerParameters compilerParameters, RunParameters runParameters) throws Exception;

    public abstract void setUpSecurity();
}
