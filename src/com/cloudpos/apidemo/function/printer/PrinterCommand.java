package com.cloudpos.apidemo.function.printer;

import p000u.aly.cv;

public class PrinterCommand {
    public static byte[] getCmdLf() {
        return new byte[]{(byte) 10};
    }

    public static byte[] getCmdHt() {
        return new byte[]{(byte) 9};
    }

    public static byte[] getCmdFf() {
        return new byte[]{(byte) 12};
    }

    public static byte[] getCmdEscJN(int n) {
        return new byte[]{(byte) 27, (byte) 74, (byte) n};
    }

    public static byte[] getCmdEscFf() {
        return new byte[]{(byte) 27, (byte) 12};
    }

    public static byte[] getCmdEscDN(int n) {
        return new byte[]{(byte) 27, (byte) 100, (byte) n};
    }

    public static byte[] getCmdSetSmallFont_CN() {
        return new byte[]{(byte) 28, (byte) 33, (byte) 1};
    }

    public static byte[] getCmdCancelSmallFont_CN() {
        return new byte[]{(byte) 28, (byte) 33, (byte) 0};
    }

    public static byte[] getCmdSetSmallFont_EN() {
        return new byte[]{(byte) 27, (byte) 33, (byte) 1};
    }

    public static byte[] getCmdCancelSmallFont_EN() {
        return new byte[]{(byte) 27, (byte) 33, (byte) 0};
    }

    public static byte[] getCmdEscN(int n) {
        return new byte[]{(byte) 27, (byte) 61, (byte) n};
    }

    public static byte[] getCmdEsc2() {
        return new byte[]{(byte) 27, (byte) 50};
    }

    public static byte[] getCmdEsc3N(int n) {
        return new byte[]{(byte) 27, (byte) 51, (byte) n};
    }

    public static byte[] getCmdEscAN(int n) {
        return new byte[]{(byte) 27, (byte) 97, (byte) n};
    }

    public static byte[] getCmdGsLNlNh(int nL, int nH) {
        return new byte[]{(byte) 29, (byte) 76, (byte) nL, (byte) nH};
    }

    public static byte[] getCmdEsc$NlNh(int nL, int nH) {
        return new byte[]{(byte) 27, (byte) 36, (byte) nL, (byte) nH};
    }

    public static byte[] getCmdEsc_N(int n) {
        return new byte[]{(byte) 27, (byte) 33, (byte) n};
    }

    public static byte[] getCmdGs_N(int n) {
        return new byte[]{(byte) 29, (byte) 33, (byte) n};
    }

    public static byte[] getCmdEscEN(int n) {
        return new byte[]{(byte) 27, (byte) 69, (byte) n};
    }

    public static byte[] getCmdEscSpN(int n) {
        return new byte[]{(byte) 27, (byte) 32, (byte) n};
    }

    public static byte[] getCmdEscSo() {
        return new byte[]{(byte) 27, cv.f423l};
    }

    public static byte[] getCmdEscDc4() {
        return new byte[]{(byte) 27, (byte) 20};
    }

    public static byte[] getCmdEsc__N(int n) {
        return new byte[]{(byte) 27, (byte) 123, (byte) n};
    }

    public static byte[] getCmdGsBN(int n) {
        return new byte[]{(byte) 29, (byte) 66, (byte) n};
    }

    public static byte[] getCmdEsc___N(int n) {
        return new byte[]{(byte) 27, (byte) 45, (byte) n};
    }

    public static byte[] getCmdEsc____N(int n) {
        return new byte[]{(byte) 27, (byte) 37, (byte) n};
    }

    public static byte[] getCmdEsc_SNMW() {
        return null;
    }

    public static byte[] getCmdEsc_____N(int n) {
        return new byte[]{(byte) 27, (byte) 37, (byte) n};
    }

    public static byte[] getCmdEscRN(int n) {
        return new byte[]{(byte) 27, (byte) 82, (byte) n};
    }

    public static byte[] getCmdEscTN(int n) {
        return new byte[]{(byte) 27, (byte) 116, (byte) n};
    }

    public static byte[] getCmdEscC5N(int n) {
        return new byte[]{(byte) 27, (byte) 99, (byte) 53, (byte) n};
    }

    public static byte[] getCmdEsc_() {
        return new byte[]{(byte) 27, (byte) 64};
    }

    public static byte[] getCmdEscVN(int n) {
        return new byte[]{(byte) 27, (byte) 118, (byte) n};
    }

    public static byte[] getCmdGsAN(int n) {
        return new byte[]{(byte) 1, (byte) 61, (byte) n};
    }

    public static byte[] getCmdEscUN(int n) {
        return new byte[]{(byte) 27, (byte) 117, (byte) n};
    }

    public static byte[] getCustomTabs() {
        return "  ".getBytes();
    }

    public static byte[] aaa() {
        return new byte[]{(byte) 27, (byte) 105};
    }
}
