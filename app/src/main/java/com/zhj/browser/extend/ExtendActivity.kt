package com.zhj.browser.extend

import android.app.Activity
import android.os.Bundle
import com.zhj.browser.R

//收藏夹、历史记录、本地保存都共用一个Activity
class ExtendActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.extend )
    }

//    获取历史记录的数据，并渲染在Activity中
//    以下两个draw函数，功能类似
    fun drawHistory() {

    }
    fun drawFavour() {

    }
    fun drawLocal() {

    }
}