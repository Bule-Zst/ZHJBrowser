package com.zhj.browser.database

import android.app.Activity
import android.arch.persistence.room.Room
import android.os.Bundle
import android.widget.Toast
import com.zhj.browser.App
import com.zhj.browser.R
import com.zhj.browser.common.info

class TestActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main )

        AppDatabase.withAppDatabase { db->
            val cate = db.getItemDao().queryFavourCategoryById( 3 )
            info( cate )
        }
    }
}