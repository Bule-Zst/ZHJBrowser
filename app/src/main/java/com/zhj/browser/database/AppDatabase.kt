package com.zhj.browser.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.zhj.browser.App

@Database( entities = [ Item::class ], version = 2 )
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao() : ItemDao
    companion object {
//        获取实例
        fun getInstance() : AppDatabase {
            return Room.databaseBuilder( App.instant, AppDatabase::class.java, "zhj.db" ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }
    }
}