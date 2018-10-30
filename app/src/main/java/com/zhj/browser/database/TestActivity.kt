package com.zhj.browser.database

import android.app.Activity
import android.arch.persistence.room.Room
import android.os.Bundle
import android.widget.Toast
import com.zhj.browser.App
import com.zhj.browser.R

class TestActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main )
    }
}

fun toast( msg: String ) {
    Toast.makeText( App.instant, msg, Toast.LENGTH_LONG ).show()
}