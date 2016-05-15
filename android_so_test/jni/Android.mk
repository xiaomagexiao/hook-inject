LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)
LOCAL_SRC_FILES:=  getJNIEnv.c
LOCAL_MODULE := bin
LOCAL_LDLIBS := -llog
include $(BUILD_EXECUTABLE)






