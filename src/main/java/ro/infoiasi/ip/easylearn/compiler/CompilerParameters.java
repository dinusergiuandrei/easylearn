package ro.infoiasi.ip.easylearn.compiler;

import ro.infoiasi.ip.easylearn.utils.Language;
import ro.infoiasi.ip.easylearn.utils.Permission;

import java.util.LinkedList;
import java.util.List;

public class CompilerParameters {
    private List<SourceFile> sourceCodes;
    private String rootDirectoryPath;
    private Language language;
    private List<Permission> permissions = new LinkedList<>();

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

    public List<Permission> getPermissions() {
        return permissions;
    }
}
