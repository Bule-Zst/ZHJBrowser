package com.zhj.browser

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.WebBackForwardList
import com.tencent.smtt.sdk.WebView
import com.zhj.browser.extend.Item

class MainActivity : Activity() {
    lateinit var mWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    通过WebView的库函数，获取历史记录，并转换成我们自定义的类列表
    fun getHistoryRecord(): Array<Item> {
        return Array<Item>( 0, { i -> Item.getDefault() } )
    }

//    跳转到扩展页面（收藏夹、历史记录、本地保存）
//    需要指定跳转到哪一个，0表示收藏夹、1表示历史记录、2表示本地保存
    fun moveToExtendActivity(view: View) {

    }
}
