package com.cosmos.coslog.common.security.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.cosmos.coslog.common.constants.SecurityConstant;
import com.cosmos.coslog.utils.Convert;
import com.cosmos.coslog.utils.StringUtils;

public class SecurityContext {
    private static final TransmittableThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = getLocalMap();
        map.put(key, value == null ? StringUtils.EMPTY : value);
    }

    public static String get(String key) {
        Map<String, Object> map = getLocalMap();
        return Convert.toStr(map.getOrDefault(key, StringUtils.EMPTY));
    }

    public static <T> T get(String key, Class<T> clazz) {
        Map<String, Object> map = getLocalMap();
        return StringUtils.cast(map.getOrDefault(key, null));
    }

    public static Map<String, Object> getLocalMap() {

        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new ConcurrentHashMap<>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static String getUsername(){
        Map<String, Object> map = getLocalMap();
        return Convert.toStr(map.getOrDefault(SecurityConstant.USER_NAME, StringUtils.EMPTY));
    }

    public static String getUserId(){
        Map<String, Object> map = getLocalMap();
        return Convert.toStr(map.getOrDefault(SecurityConstant.USER_ID, StringUtils.EMPTY));
    }

    public static void setUsername(String username){
        set(SecurityConstant.USER_NAME, username);
    }

    public static void setUserId(String userId){
        set(SecurityConstant.USER_ID, userId);
    }
}
