//package com.zhj.browser.database
//
//import android.app.Activity
//import android.arch.persistence.room.Room
//import android.os.Bundle
//import android.widget.Toast
//import com.zhj.browser.App
//import com.zhj.browser.R
//import com.zhj.browser.common.info
//
//class TestActivity : Activity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView( R.layout.activity_main )
//
////        todo: 用于更新数据库，先执行一下这个，不然执行项目会报错
//        var db = Room.databaseBuilder( App.instance, AppDatabase::class.java, "zhj.db" ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
//        var id = db.getMatchUrlDao().insert( MatchUrl.getDefault() )
//        db.close()
//        info( String.format( "insert success, id = %d", id ) )
//
//        db = Room.databaseBuilder( App.instance, AppDatabase::class.java, "zhj.db" ).allowMainThreadQueries().build()
//        id = db.getMatchUrlDao().insert( MatchUrl.getDefault() )
//        db.close()
//        info( String.format( "insert success, id = %d", id ) )
//    }
//}