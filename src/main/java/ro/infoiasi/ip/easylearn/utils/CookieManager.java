package ro.infoiasi.ip.easylearn.utils;

import javax.servlet.http.Cookie;

public class CookieManager {
    public static Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies != null && cookies.length > 0)
            for (Cookie cke : cookies)
                if (cke!= null && cke.getName().equals(name))
                    return cke;

        return null;
    }
}
