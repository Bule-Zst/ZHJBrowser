package com.zhj.browser.extend

import android.graphics.Bitmap

//扩展页面中的每条数据，由图标、标题、路径构成
data class Item(
        var bitmap: Bitmap?,
        var title: String,
        var url: String
) {
    companion object {
        fun getDefault() : Item {
            return Item( null, "", "" )
        }
    }
}