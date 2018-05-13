package ro.infoiasi.ip.easylearn.utils;

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
}
