#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_microsoft_emmx_MainActivity_stringFromJNI(
       //参数说明
       // 1. JNIEnv：代表了VM里面的环境，本地的代码可以通过该参数与Java代码进行操作
       // 2. obj：定义JNI方法的类的一个本地引用（this）
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
