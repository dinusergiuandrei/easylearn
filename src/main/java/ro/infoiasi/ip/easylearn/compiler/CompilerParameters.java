package ro.infoiasi.ip.easylearn.compiler;

import ro.infoiasi.ip.easylearn.utils.Language;

import java.util.List;

public class CompilerParameters {
    private List<SourceFile> sourceCodes;
    private String rootDirectoryPath;
    private Language language;

    public CompilerParameters(Language language, List<SourceFile> sourceCodes, String rootDirectoryPath) {
        this.language = language;
        this.sourceCodes = sourceCodes;
        this.rootDirectoryPath = rootDirectoryPath;
    }

    public List<SourceFile> getSourceCodes() {
        return sourceCodes;
    }

    public String getRootDirectoryPath() {
        return rootDirectoryPath;
    }

    public Language getLanguage() {
        return language;
    }
}
