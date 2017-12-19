package com.android.common.utils;

import java.util.Random;

public class RandomUtils {
    public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBERS = "0123456789";
    public static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getRandomNumbersAndLetters(int length) {
        return getRandom(NUMBERS_AND_LETTERS, length);
    }

    public static String getRandomNumbers(int length) {
        return getRandom(NUMBERS, length);
    }

    public static String getRandomLetters(int length) {
        return getRandom(LETTERS, length);
    }

    public static String getRandomCapitalLetters(int length) {
        return getRandom(CAPITAL_LETTERS, length);
    }

    public static String getRandomLowerCaseLetters(int length) {
        return getRandom(LOWER_CASE_LETTERS, length);
    }

    public static String getRandom(String source, int length) {
        return StringUtils.isEmpty(source) ? null : getRandom(source.toCharArray(), length);
    }

    public static String getRandom(char[] sourceChar, int length) {
        if (sourceChar == null || sourceChar.length == 0 || length < 0) {
            return null;
        }
        StringBuilder str = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(sourceChar[random.nextInt(sourceChar.length)]);
        }
        return str.toString();
    }

    public static int getRandom(int max) {
        return getRandom(0, max);
    }

    public static int getRandom(int min, int max) {
        if (min > max) {
            return 0;
        }
        return min != max ? min + new Random().nextInt(max - min) : min;
    }

    public static boolean shuffle(Object[] objArray) {
        if (objArray == null) {
            return false;
        }
        return shuffle(objArray, getRandom(objArray.length));
    }

    public static boolean shuffle(Object[] objArray, int shuffleCount) {
        if (objArray != null && shuffleCount >= 0) {
            int length = objArray.length;
            if (length >= shuffleCount) {
                for (int i = 1; i <= shuffleCount; i++) {
                    int random = getRandom(length - i);
                    Object temp = objArray[length - i];
                    objArray[length - i] = objArray[random];
                    objArray[random] = temp;
                }
                return true;
            }
        }
        return false;
    }

    public static int[] shuffle(int[] intArray) {
        if (intArray == null) {
            return null;
        }
        return shuffle(intArray, getRandom(intArray.length));
    }

    public static int[] shuffle(int[] intArray, int shuffleCount) {
        if (intArray != null && shuffleCount >= 0) {
            int length = intArray.length;
            if (length >= shuffleCount) {
                int[] out = new int[shuffleCount];
                for (int i = 1; i <= shuffleCount; i++) {
                    int random = getRandom(length - i);
                    out[i - 1] = intArray[random];
                    int temp = intArray[length - i];
                    intArray[length - i] = intArray[random];
                    intArray[random] = temp;
                }
                return out;
            }
        }
        return null;
    }
}
