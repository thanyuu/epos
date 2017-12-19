package com.android.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {
    public static boolean isPrintException = true;

    public static Long getLong(JSONObject jsonObject, String key, Long defaultValue) {
        if (!(jsonObject == null || StringUtils.isEmpty(key))) {
            try {
                defaultValue = Long.valueOf(jsonObject.getLong(key));
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    public static Long getLong(String jsonData, String key, Long defaultValue) {
        if (!StringUtils.isEmpty(jsonData)) {
            try {
                defaultValue = getLong(new JSONObject(jsonData), key, defaultValue);
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    public static long getLong(JSONObject jsonObject, String key, long defaultValue) {
        return getLong(jsonObject, key, Long.valueOf(defaultValue)).longValue();
    }

    public static long getLong(String jsonData, String key, long defaultValue) {
        return getLong(jsonData, key, Long.valueOf(defaultValue)).longValue();
    }

    public static Integer getInt(JSONObject jsonObject, String key, Integer defaultValue) {
        if (!(jsonObject == null || StringUtils.isEmpty(key))) {
            try {
                defaultValue = Integer.valueOf(jsonObject.getInt(key));
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    public static Integer getInt(String jsonData, String key, Integer defaultValue) {
        if (!StringUtils.isEmpty(jsonData)) {
            try {
                defaultValue = getInt(new JSONObject(jsonData), key, defaultValue);
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    public static int getInt(JSONObject jsonObject, String key, int defaultValue) {
        return getInt(jsonObject, key, Integer.valueOf(defaultValue)).intValue();
    }

    public static int getInt(String jsonData, String key, int defaultValue) {
        return getInt(jsonData, key, Integer.valueOf(defaultValue)).intValue();
    }

    public static Double getDouble(JSONObject jsonObject, String key, Double defaultValue) {
        if (!(jsonObject == null || StringUtils.isEmpty(key))) {
            try {
                defaultValue = Double.valueOf(jsonObject.getDouble(key));
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    public static Double getDouble(String jsonData, String key, Double defaultValue) {
        if (!StringUtils.isEmpty(jsonData)) {
            try {
                defaultValue = getDouble(new JSONObject(jsonData), key, defaultValue);
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    public static double getDouble(JSONObject jsonObject, String key, double defaultValue) {
        return getDouble(jsonObject, key, Double.valueOf(defaultValue)).doubleValue();
    }

    public static double getDouble(String jsonData, String key, double defaultValue) {
        return getDouble(jsonData, key, Double.valueOf(defaultValue)).doubleValue();
    }

    public static String getString(JSONObject jsonObject, String key, String defaultValue) {
        if (!(jsonObject == null || StringUtils.isEmpty(key))) {
            try {
                defaultValue = jsonObject.getString(key);
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    public static String getString(String jsonData, String key, String defaultValue) {
        if (!StringUtils.isEmpty(jsonData)) {
            try {
                defaultValue = getString(new JSONObject(jsonData), key, defaultValue);
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    public static String[] getStringArray(JSONObject jsonObject, String key, String[] defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue;
        }
        try {
            JSONArray statusArray = jsonObject.getJSONArray(key);
            if (statusArray == null) {
                return defaultValue;
            }
            String[] value = new String[statusArray.length()];
            for (int i = 0; i < statusArray.length(); i++) {
                value[i] = statusArray.getString(i);
            }
            return value;
        } catch (JSONException e) {
            if (!isPrintException) {
                return defaultValue;
            }
            e.printStackTrace();
            return defaultValue;
        }
    }

    public static String[] getStringArray(String jsonData, String key, String[] defaultValue) {
        if (!StringUtils.isEmpty(jsonData)) {
            try {
                defaultValue = getStringArray(new JSONObject(jsonData), key, defaultValue);
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    public static JSONObject getJSONObject(JSONObject jsonObject, String key, JSONObject defaultValue) {
        if (!(jsonObject == null || StringUtils.isEmpty(key))) {
            try {
                defaultValue = jsonObject.getJSONObject(key);
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    public static JSONObject getJSONObject(String jsonData, String key, JSONObject defaultValue) {
        if (!StringUtils.isEmpty(jsonData)) {
            try {
                defaultValue = getJSONObject(new JSONObject(jsonData), key, defaultValue);
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    public static JSONArray getJSONArray(JSONObject jsonObject, String key, JSONArray defaultValue) {
        if (!(jsonObject == null || StringUtils.isEmpty(key))) {
            try {
                defaultValue = jsonObject.getJSONArray(key);
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    public static JSONArray getJSONArray(String jsonData, String key, JSONArray defaultValue) {
        if (!StringUtils.isEmpty(jsonData)) {
            try {
                defaultValue = getJSONArray(new JSONObject(jsonData), key, defaultValue);
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return defaultValue;
    }

    public static boolean getBoolean(JSONObject jsonObject, String key, Boolean defaultValue) {
        if (jsonObject == null || StringUtils.isEmpty(key)) {
            return defaultValue.booleanValue();
        }
        try {
            return jsonObject.getBoolean(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue.booleanValue();
        }
    }

    public static boolean getBoolean(String jsonData, String key, Boolean defaultValue) {
        if (StringUtils.isEmpty(jsonData)) {
            return defaultValue.booleanValue();
        }
        try {
            return getBoolean(new JSONObject(jsonData), key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue.booleanValue();
        }
    }

    public static Map<String, String> getMap(JSONObject jsonObject, String key) {
        return parseKeyAndValueToMap(getString(jsonObject, key, null));
    }

    public static Map<String, String> getMap(String jsonData, String key) {
        Map<String, String> map = null;
        if (jsonData == null) {
            return map;
        }
        if (jsonData.length() == 0) {
            return new HashMap();
        }
        try {
            return getMap(new JSONObject(jsonData), key);
        } catch (JSONException e) {
            if (!isPrintException) {
                return map;
            }
            e.printStackTrace();
            return map;
        }
    }

    public static Map<String, String> parseKeyAndValueToMap(JSONObject sourceObj) {
        if (sourceObj == null) {
            return null;
        }
        Map<String, String> keyAndValueMap = new HashMap();
        Iterator iter = sourceObj.keys();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            MapUtils.putMapNotEmptyKey(keyAndValueMap, key, getString(sourceObj, key, ""));
        }
        return keyAndValueMap;
    }

    public static Map<String, String> parseKeyAndValueToMap(String source) {
        Map<String, String> map = null;
        if (!StringUtils.isEmpty(source)) {
            try {
                map = parseKeyAndValueToMap(new JSONObject(source));
            } catch (JSONException e) {
                if (isPrintException) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}
