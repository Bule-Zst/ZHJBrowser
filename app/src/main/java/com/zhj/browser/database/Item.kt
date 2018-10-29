package com.zhj.browser.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.graphics.Bitmap

@Entity( tableName = "item" )
data class Item(
        @PrimaryKey( autoGenerate = true )
        var id: Int,
//        图标路径
        var bitmapPath: String,
        var title: String,
        var url: String,
//        类别：收藏夹、历史记录
        var category: Int
) {
    companion object {
        fun getDefault() : com.zhj.browser.database.Item {
            return Item( 0, "", "", "", 0 )
        }
    }
}