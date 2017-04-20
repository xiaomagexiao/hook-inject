pwd=`pwd`

libname="android_zygote_injection-master"
soname="android_dex_injection-master.so"
echo "cd ../${libname}/jni"
cd ../${libname}/jni
ndk-build
cd ${pwd}

pwd

cp -f ../${libname}/libs/armeabi-v7a/${libname} .

adb push ${libname} /data/local/tmp/${libname}
adb push ${soname} /data/local/tmp/${soname}
adb shell chmod 777 /data/local/tmp/${soname}
adb shell chmod 777 /data/local/tmp/${libname}

adb shell /data/local/tmp/${libname}
