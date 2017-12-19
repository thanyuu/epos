package com.android.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtils {
    public static final String DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR = ",";
    public static final String DEFAULT_KEY_AND_VALUE_SEPARATOR = ":";

    public static <K, V> boolean isEmpty(Map<K, V> sourceMap) {
        return sourceMap == null || sourceMap.size() == 0;
    }

    public static boolean putMapNotEmptyKey(Map<String, String> map, String key, String value) {
        if (map == null || StringUtils.isEmpty(key)) {
            return false;
        }
        map.put(key, value);
        return true;
    }

    public static boolean putMapNotEmptyKeyAndValue(Map<String, String> map, String key, String value) {
        if (map == null || StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return false;
        }
        map.put(key, value);
        return true;
    }

    public static boolean putMapNotEmptyKeyAndValue(Map<String, String> map, String key, String value, String defaultValue) {
        if (map == null || StringUtils.isEmpty(key)) {
            return false;
        }
        if (!StringUtils.isEmpty(value)) {
            defaultValue = value;
        }
        map.put(key, defaultValue);
        return true;
    }

    public static <K, V> boolean putMapNotNullKey(Map<K, V> map, K key, V value) {
        if (map == null || key == null) {
            return false;
        }
        map.put(key, value);
        return true;
    }

    public static <K, V> boolean putMapNotNullKeyAndValue(Map<K, V> map, K key, V value) {
        if (map == null || key == null || value == null) {
            return false;
        }
        map.put(key, value);
        return true;
    }

    public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        if (isEmpty(map)) {
            return null;
        }
        for (Entry<K, V> entry : map.entrySet()) {
            if (ObjectUtils.isEquals(entry.getValue(), value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static Map<String, String> parseKeyAndValueToMap(String source, String keyAndValueSeparator, String keyAndValuePairSeparator, boolean ignoreSpace) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        if (StringUtils.isEmpty(keyAndValueSeparator)) {
            keyAndValueSeparator = DEFAULT_KEY_AND_VALUE_SEPARATOR;
        }
        if (StringUtils.isEmpty(keyAndValuePairSeparator)) {
            keyAndValuePairSeparator = ",";
        }
        Map<String, String> keyAndValueMap = new HashMap();
        String[] keyAndValueArray = source.split(keyAndValuePairSeparator);
        if (keyAndValueArray == null) {
            return null;
        }
        for (String valueEntity : keyAndValueArray) {
            if (!StringUtils.isEmpty(valueEntity)) {
                int seperator = valueEntity.indexOf(keyAndValueSeparator);
                if (seperator != -1) {
                    if (ignoreSpace) {
                        putMapNotEmptyKey(keyAndValueMap, valueEntity.substring(0, seperator).trim(), valueEntity.substring(seperator + 1).trim());
                    } else {
                        putMapNotEmptyKey(keyAndValueMap, valueEntity.substring(0, seperator), valueEntity.substring(seperator + 1));
                    }
                }
            }
        }
        return keyAndValueMap;
    }

    public static Map<String, String> parseKeyAndValueToMap(String source, boolean ignoreSpace) {
        return parseKeyAndValueToMap(source, DEFAULT_KEY_AND_VALUE_SEPARATOR, ",", ignoreSpace);
    }

    public static Map<String, String> parseKeyAndValueToMap(String source) {
        return parseKeyAndValueToMap(source, DEFAULT_KEY_AND_VALUE_SEPARATOR, ",", true);
    }

    public static String toJson(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuilder paras = new StringBuilder();
        paras.append("{");
        Iterator<Entry<String, String>> ite = map.entrySet().iterator();
        while (ite.hasNext()) {
            Entry<String, String> entry = (Entry) ite.next();
            paras.append("\"").append((String) entry.getKey()).append("\":\"").append((String) entry.getValue()).append("\"");
            if (ite.hasNext()) {
                paras.append(",");
            }
        }
        paras.append("}");
        return paras.toString();
    }
}
