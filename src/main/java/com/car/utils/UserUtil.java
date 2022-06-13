package com.car.utils;


/**
 * user info util
 *
 * @author Gandalf
 * @since 2022/6/13
 */
public class UserUtil {

    private static ThreadLocal contextHolder = new ThreadLocal();

    public static void setUid(String uid) {
        contextHolder.set(uid);
    }

    public static String getUid() {
        return (String) contextHolder.get();
    }

    public static void remove() {
        contextHolder.remove();
    }
}

