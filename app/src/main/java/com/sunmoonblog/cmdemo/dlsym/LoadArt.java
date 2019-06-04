package com.sunmoonblog.cmdemo.dlsym;

public class LoadArt {
    static {
        System.loadLibrary("hello-jni");
    }

    public static native void loadArt();

    public static native void fakeLoadArt();

    public static native void loadOwnSo(String soPath);
    public static native void fakeLoadOwnSo(String soPath);
}
