package com.zhj.browser.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity( tableName = "favour_category" )
data class FavourCategory(
        @PrimaryKey( autoGenerate = true )
        var id: Int,
        var category: String
) {
    companion object {
        fun getDefault() = FavourCategory( 0, "未分类" )
    }

//    唯一性添加，先查询是否存在，如果不存在，就添加
    fun insert() {
        AppDatabase.withAppDatabase { db->
            val list = db.getFavoutCategoryDao().queryByCategory( this.category )
            if( list.size == 0 ) {
                db.getFavoutCategoryDao().insert( this )
            }
        }
    }
}