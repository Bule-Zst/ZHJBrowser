package com.zhj.browser.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.zhj.browser.App

@Database( entities = [ Item::class, MatchUrl::class, FavourCategory::class ], version = 1 )
abstract class AppDatabase : RoomDatabase() {
    abstract fun getItemDao() : ItemDao
    abstract fun getMatchUrlDao() : MatchUrlDao
    abstract fun getFavoutCategoryDao(): FavourCategoryDao
    companion object {
//        获取实例
        fun getInstance() : AppDatabase {
            return Room.databaseBuilder( App.instance, AppDatabase::class.java, "zhj.db" ).allowMainThreadQueries().build()
        }

        fun<T> withAppDatabase( f: ( app: AppDatabase ) -> T ) {
            val db = AppDatabase.getInstance()
            f.invoke( db )
            db.close()
        }
    }
}

