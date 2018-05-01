package com.woshua.core.utils;

import javax.servlet.http.Cookie;

/**
 * Created by Acer on 2017/3/27.
 */
public class CookieUtils {

    public static final String ID_NAME = "woshua_id";
    public static final String KEY_NAME = "woshua_key";
    public static final String ACCOUNT_NAME = "woshua_account";

    public static final String VIEW_HISTORY_COOKIE_KEY = "client_view_history";
    public static final int COOKIE_AGE = 30 * 24 * 60 * 60;
    public static final String COOKIE_PATH = "/";
    public static final String WEB_SITE = "www.mltr.com.cn";
    public static final String WEB_DOMAIN = "mltr.com.cn";

    /**
     * 根据cookie名字取得cookie
     *
     * @param name cookie名字
     * @return cookie
     * @author fengyuan
     */
    public static Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                String cookName = cookie.getName();
                if (cookName != null && cookName.equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static String createCookieKey(Long id, String account, String ip) {
        return SecureUtils.sha1Hex(id + account + ip);
    }
}
