package com.android.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListUtils {
    public static final String DEFAULT_JOIN_SEPARATOR = ",";

    public static <V> int getSize(List<V> sourceList) {
        return sourceList == null ? 0 : sourceList.size();
    }

    public static <V> boolean isEmpty(List<V> sourceList) {
        return sourceList == null || sourceList.size() == 0;
    }

    public static <V> boolean isEquals(ArrayList<V> actual, ArrayList<V> expected) {
        if (actual == null) {
            if (expected == null) {
                return true;
            }
            return false;
        } else if (expected == null) {
            return false;
        } else {
            if (actual.size() != expected.size()) {
                return false;
            }
            for (int i = 0; i < actual.size(); i++) {
                if (!ObjectUtils.isEquals(actual.get(i), expected.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    public static String join(List<String> list) {
        return join((List) list, ",");
    }

    public static String join(List<String> list, char separator) {
        return join((List) list, new String(new char[]{separator}));
    }

    public static String join(List<String> list, String separator) {
        if (isEmpty(list)) {
            return "";
        }
        if (separator == null) {
            separator = ",";
        }
        StringBuilder joinStr = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            joinStr.append((String) list.get(i));
            if (i != list.size() - 1) {
                joinStr.append(separator);
            }
        }
        return joinStr.toString();
    }

    public static <V> boolean addDistinctEntry(List<V> sourceList, V entry) {
        return (sourceList == null || sourceList.contains(entry)) ? false : sourceList.add(entry);
    }

    public static <V> int addDistinctList(List<V> sourceList, List<V> entryList) {
        if (sourceList == null || isEmpty(entryList)) {
            return 0;
        }
        int sourceCount = sourceList.size();
        Iterator it = entryList.iterator();
        while (it.hasNext()) {
            Object entry = it.next();
            if (!sourceList.contains(entry)) {
                sourceList.add(entry);
            }
        }
        return sourceList.size() - sourceCount;
    }

    public static <V> int distinctList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return 0;
        }
        int sourceCount = sourceList.size();
        int sourceListSize = sourceList.size();
        for (int i = 0; i < sourceListSize; i++) {
            int j = i + 1;
            while (j < sourceListSize) {
                if (sourceList.get(i).equals(sourceList.get(j))) {
                    sourceList.remove(j);
                    sourceListSize = sourceList.size();
                    j--;
                }
                j++;
            }
        }
        return sourceCount - sourceList.size();
    }

    public static <V> boolean addListNotNullValue(List<V> sourceList, V value) {
        return (sourceList == null || value == null) ? false : sourceList.add(value);
    }

    public static <V> V getLast(List<V> sourceList, V value) {
        return sourceList == null ? null : ArrayUtils.getLast(sourceList.toArray(), value, true);
    }

    public static <V> V getNext(List<V> sourceList, V value) {
        return sourceList == null ? null : ArrayUtils.getNext(sourceList.toArray(), value, true);
    }

    public static <V> List<V> invertList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            return sourceList;
        }
        List<V> invertList = new ArrayList(sourceList.size());
        for (int i = sourceList.size() - 1; i >= 0; i--) {
            invertList.add(sourceList.get(i));
        }
        return invertList;
    }
}
