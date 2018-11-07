package com.zhj.browser.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.graphics.Bitmap

//扩展页面中的每条数据，由图标、标题、路径构成
@Entity( tableName = "item" )
data class Item(
        @PrimaryKey( autoGenerate = true )
        var id: Int,
//        图标路径
        var bitmapPath: String,
        var title: String,
        var url: String,
//        类别：收藏夹(0)、历史记录(1)
        var category: Int,
        var favourCategory: String
) {
    companion object {
        fun getDefault() : com.zhj.browser.database.Item {
            return Item( 0, "", "", "", 0, "" )
        }
//        类别常量
        val FAVOUR = 0
        val HISTORY = 1
        val LOCAL = 2
    }
}

fun List<Item>.toMsg() : String {
    var res = ""
    for( item in this ) {
        res += item.toString() + "\n"
    }
    return res
}