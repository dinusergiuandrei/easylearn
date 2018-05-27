package ro.infoiasi.ip.easylearn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleLogger {
    private static final Boolean SWITCH = true;

    public static void Log(Object obj) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").format(new Date()) +"  LOGG      "+obj.toString());
    }

}
