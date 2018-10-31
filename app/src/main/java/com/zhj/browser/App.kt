package com.zhj.browser

import android.app.Application

//全局的context，方便代码编写
class App: Application() {
    companion object {
        lateinit var instance : App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}