package ro.infoiasi.ip.easylearn.utils;

import java.util.Random;

public class RandomString {
    private final static String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public static String RandomStr(int len)
    {
        StringBuilder randStr = new StringBuilder();
        for(int i=0;i<len; i++)
            randStr.append(symbols.charAt(new Random().nextInt(symbols.length())));
        return randStr.toString();
    }
}
