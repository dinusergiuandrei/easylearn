package ro.infoiasi.ip.easylearn.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Permission {
    private String title;
    private List<String> args = new LinkedList<>();

    public Permission(String title, String... args) {
        this.title = title;
        this.args.addAll(Arrays.asList(args));
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public String getPermissionLine() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("permission ").append(title).append(" ");
        if (args != null) {
            for (int i = 0; i < this.args.size(); i++) {
                stringBuilder.append(args.get(i));
                if(i<this.args.size()-1)
                    stringBuilder.append(", ");
            }
        }

        stringBuilder.append(";\n");
        return stringBuilder.toString();
    }
}
