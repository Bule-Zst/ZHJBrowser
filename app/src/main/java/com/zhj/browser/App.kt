package com.zhj.browser

import android.app.Application
import android.util.Log
import java.time.Instant

//全局的context，方便代码编写
class App: Application() {
    companion object {
        lateinit var instant : App
    }

    override fun onCreate() {
        super.onCreate()
        instant = this
    }
}

fun log( msg: String ) {
    Log.i( "Bule", msg )
}

fun debug( msg: String ) {
    Log.d( "Bule", msg )
}