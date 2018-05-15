package ro.infoiasi.ip.easylearn.utils;

import ro.infoiasi.ip.easylearn.utils.CommandBuilder.CommandBuilder;
import ro.infoiasi.ip.easylearn.utils.CommandBuilder.CppCommandBuilder;
import ro.infoiasi.ip.easylearn.utils.CommandBuilder.JavaCommandBuilder;
import ro.infoiasi.ip.easylearn.utils.CommandBuilder.PythonCommandBuilder;

public enum Language {
    Java("Java"),
    Cpp("Cpp"),
    Python("Python");

    String value;

    Language(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public CommandBuilder getCommandBuilder(){
        switch (this){
            case Java: return new JavaCommandBuilder();
            case Cpp: return new CppCommandBuilder();
            case Python: return new PythonCommandBuilder();
        }
        return null;
    }
}
