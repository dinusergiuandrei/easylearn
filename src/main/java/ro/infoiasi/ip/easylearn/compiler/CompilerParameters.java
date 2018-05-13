package ro.infoiasi.ip.easylearn.compiler;

import ro.infoiasi.ip.easylearn.utils.Language;

import java.util.List;

public class CompilerParameters {
    private List<SourceFile> sourceCodes;
    private String compileOutputPath;
    private Language language;

    public CompilerParameters(Language language, List<SourceFile> sourceCodes, String compileOutputPath) {
        this.language = language;
        this.sourceCodes = sourceCodes;
        this.compileOutputPath = compileOutputPath;
    }

    public List<SourceFile> getSourceCodes() {
        return sourceCodes;
    }

    public String getCompileOutputPath() {
        return compileOutputPath;
    }

    public Language getLanguage() {
        return language;
    }
}
