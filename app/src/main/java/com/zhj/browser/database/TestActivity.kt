package com.zhj.browser.database

import android.app.Activity
import android.arch.persistence.room.Room
import android.os.Bundle
import com.zhj.browser.App
import com.zhj.browser.R

class TestActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main )

        val db = AppDatabase.getInstance()
        db.getDao().queryPart()
        db.close()
    }
}