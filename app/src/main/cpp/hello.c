//
// Created by 410063005@163.com on 2019/6/4.
//
#include <jni.h>
#include <android/log.h>
#include <dlfcn.h>
#include "fake_dlfcn.h"

#define LOGI(...)   __android_log_print((int)ANDROID_LOG_INFO, "HelloJni", __VA_ARGS__)
#define LOGE(...)   __android_log_print((int)ANDROID_LOG_ERROR, "HelloJni", __VA_ARGS__)

typedef int (*CACULATE_FUNC)(int, int);

JNIEXPORT void JNICALL
Java_com_sunmoonblog_cmdemo_dlsym_LoadArt_loadArt(JNIEnv *env, jclass type) {
    void* handle = 0;
    if (sizeof(void*) == sizeof(uint64_t)) {
        LOGI("64 bit mode.");
        dlopen("/system/lib64/libart.so", RTLD_NOW);
    } else {
        LOGI("32 bit mode.");
        dlopen("/system/lib/libart.so", RTLD_NOW);
    }

    if (handle == 0) {
        LOGE("dlopen() libart.so failed!");
    } else {
        LOGI("dlopen() libart.so success!");
        dlclose(handle);
    }
}

JNIEXPORT void JNICALL
Java_com_sunmoonblog_cmdemo_dlsym_LoadArt_fakeLoadArt(JNIEnv *env, jclass type) {
    void* handle = 0;
    if (sizeof(void*) == sizeof(uint64_t)) {
        LOGI("64 bit mode.");
        handle = fake_dlopen("/system/lib64/libart.so", RTLD_NOW);
    } else {
        LOGI("32 bit mode.");
        handle = fake_dlopen("/system/lib/libart.so", RTLD_NOW);
    }

    if (handle == 0) {
        LOGE("fake_dlopen() libart.so failed!");
    } else {
        LOGI("fake_dlopen() libart.so success!");
    }
}

JNIEXPORT void JNICALL
Java_com_sunmoonblog_cmdemo_dlsym_LoadArt_loadOwnSo(JNIEnv *env, jclass type, jstring so_path) {
    if (sizeof(void*) == sizeof(uint64_t)) {
        LOGI("64 bit mode.");
    } else {
        LOGI("32 bit mode.");
    }

    char* error_msg;
    const char *so_path_str = (*env)->GetStringUTFChars(env, so_path, 0);

    LOGI("so_path_str is %s\n", so_path_str);

    void* handle = 0;
    handle = dlopen(so_path_str, RTLD_NOW);

    if (handle == 0) {
        LOGE("dlopen() libop-jni.so failed!");
    } else {
        LOGI("dlopen() libop-jni.so success!");

    }

    void* sym_add = dlsym(handle, "add");
    if (sym_add == 0) {
        LOGE("dlsym error\n");

        if ((error_msg = dlerror()) != 0) {
            LOGE("error msg %s\n", error_msg);
        }
    } else {
        LOGI("dlsym success!");
        CACULATE_FUNC add_func = sym_add;
        LOGI("%d + %d = %d\n", 1, 11, add_func(11, 1));
    }

    if (handle != 0) {
        dlclose(handle);
    }
}

JNIEXPORT void JNICALL
Java_com_sunmoonblog_cmdemo_dlsym_LoadArt_fakeLoadOwnSo(JNIEnv *env, jclass type, jstring so_path) {
    if (sizeof(void*) == sizeof(uint64_t)) {
        LOGI("64 bit mode.");
    } else {
        LOGI("32 bit mode.");
    }

    const char *so_path_str = (*env)->GetStringUTFChars(env, so_path, 0);

    void* handle = 0;
    handle = fake_dlopen(so_path_str, RTLD_NOW);

    if (handle == 0) {
        LOGE("fake_dlopen() libop-jni.so failed!");
    } else {
        LOGI("fake_dlopen() libop-jni.so success!");
    }
}