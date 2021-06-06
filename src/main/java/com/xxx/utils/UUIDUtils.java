package com.xxx.utils;

import java.util.UUID;

public class UUIDUtils {

    public static String getUUID_() { // 带 "-"
        return UUID.randomUUID().toString();
    }

    public static String getUUID() { // 不带 "-"
        return UUID.randomUUID().toString().replace("-","");
    }

    public static String getUUIDArg(String arg) {
        return arg + UUID.randomUUID().toString().replace("-","");
    }

    public static String getUUIDArg_(String arg) {
        return arg + UUID.randomUUID().toString();
    }

}
