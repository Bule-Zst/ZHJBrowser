package com.zhj.browser.extend

import android.app.Activity
import android.os.Bundle
import com.zhj.browser.R

//收藏夹、历史记录、本地保存都共用一个Activity
class ExtendActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.extend )

//        根据MainActivity的传参决定渲染哪个功能
        val choose = this.intent.getIntExtra( "choose", 0 )
        when( choose ) {
            0 -> {
                drawFavour()
            }
            1 -> {
                drawHistory()
            }
            2 -> {
                drawLocal()
            }
        }
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