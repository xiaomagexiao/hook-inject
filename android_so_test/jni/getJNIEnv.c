#include <jni.h>
#include <stdio.h>
#include <dlfcn.h>
#include <android/log.h>
/*
JNI_VERSION_1_1 0x00010001
JNI_VERSION_1_2 0x00010002
JNI_VERSION_1_4 0x00010004
JNI_VERSION_1_6 0x00010006*/

#define ENABLE_LOG 1 
#if ENABLE_LOG
#define LOGE(...) ((void) __android_log_print(ANDROID_LOG_ERROR, "Dex_Injection", __VA_ARGS__))
#define LOGD(...) ((void) __android_log_print(ANDROID_LOG_DEBUG, "Dex_Injection", __VA_ARGS__))
#define LOGI(...) ((void) __android_log_print(ANDROID_LOG_INFO, "Dex_Injection", __VA_ARGS__))
#else 
#define LOGE(format, args...)
#define LOGD(format, args...)
#define LOGI(format, args...)
#endif

int main(int argc, char* argv[]) {
LOGE("MAGE[+] execute main \n");
}
