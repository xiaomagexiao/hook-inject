pwd=`pwd`

libname="android_zygote_injection-master"

echo "cd ../${libname}/jni"
cd ../${libname}/jni
ndk-build
cd ${pwd}

pwd

cp -f ../${libname}/libs/armeabi-v7a/${libname} .

adb push ${libname} /data/local/tmp/${libname}

adb shell chmod 777 /data/local/tmp/${libname}

adb shell /data/local/tmp/${libname}
