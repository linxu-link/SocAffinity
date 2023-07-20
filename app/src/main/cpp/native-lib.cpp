#include <jni.h>
#include <unistd.h>
#include <pthread.h>

// 获取cpu核心数
int getCores() {
    int cores = sysconf(_SC_NPROCESSORS_CONF);
    return cores;
}

extern "C" JNIEXPORT jint JNICALL Java_com_wj_socaffinity_ThreadAffinity_getCores(JNIEnv *env, jobject thiz){
    return getCores();
}
// 绑定线程到指定cpu
extern "C" JNIEXPORT jint JNICALL Java_com_wj_socaffinity_ThreadAffinity_bindThreadToCore(JNIEnv *env, jobject thiz, jint core) {
    int num = getCores();
    if (core >= num) {
        return -1;
    }
    cpu_set_t mask;
    CPU_ZERO(&mask);
    CPU_SET(core, &mask);
    if (sched_setaffinity(0, sizeof(mask), &mask) == -1) {
        return -1;
    }
    return 0;
}

// 绑定进程程到指定cpu
extern "C"
JNIEXPORT jint JNICALL
Java_com_wj_socaffinity_ThreadAffinity_bindPidToCore(JNIEnv *env, jobject thiz, jint pid,
                                                     jint core) {
    int num = getCores();
    if (core >= num) {
        return -1;
    }
    cpu_set_t mask;
    CPU_ZERO(&mask);
    CPU_SET(core, &mask);
    if (sched_setaffinity(pid, sizeof(mask), &mask) == -1) {
        return -1;
    }
    return 0;
}