#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_plantatree_1team_12_11_plantatreetestapp_PlantATreeApp_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}