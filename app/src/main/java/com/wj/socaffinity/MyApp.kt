package com.wj.socaffinity

import android.app.Application
import android.content.Context

class MyApp: Application() {

    override fun onCreate() {
        // 注意确定你的CPU核心 大核心、小核心的标号。
        ThreadAffinity.pidToCore(android.os.Process.myPid(), 2)
        super.onCreate()
    }
}