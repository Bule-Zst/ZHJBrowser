package com.zhj.browser

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import com.zhj.browser.database.AppDatabase
import com.zhj.browser.database.Item
import com.zhj.browser.tool.BitmapTool
import com.zhj.browser.extend.ExtendActivity

class MainActivity : Activity() {
    lateinit var mWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mWebView = findViewById( R.id.web_view )
        addListen()
        mWebView.loadUrl( "https://www.baidu.com/s?ie=UTF-8&wd=android%20%E4%BF%9D%E5%AD%98bitmap" )
    }

//    为WebView对象的Url跳转添加监听事件
    fun addListen() {
        mWebView.webViewClient = object : WebViewClient() {
            private var if_load: Boolean = false
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished( view, url )

                if( !if_load ) {
                    return;
                }

                val bitmap = view.favicon
                val title = view.title
                val mUrl = view.url
                val bitmapPath =  if( bitmap == null ) "" else { BitmapTool.saveBitmap( bitmap ) }

                val db = AppDatabase.getInstance()
                db.getDao().insert( with( Item.getDefault() ) {
                    this.bitmapPath = if( bitmapPath == null ) "" else { bitmapPath }
                    this.url = mUrl
                    this.title = title
                    this.category = 1
                    this
                } )
                db.close()

                if_load = false
            }

            override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
                super.onPageStarted(p0, p1, p2)
                if_load = true
            }

            override fun shouldOverrideUrlLoading(p0: WebView?, p1: String?): Boolean {
                if_load = false
                return super.shouldOverrideUrlLoading(p0, p1)

            }
        }
    }

//    跳转到扩展页面（收藏夹、历史记录、本地保存）
//    需要指定跳转到哪一个，0表示收藏夹、1表示历史记录、2表示本地保存。关键词：choose
    fun moveToExtendActivity( view: View ) {
        val viewId = view.id
//        根据id获取组件，根据组件名称，决定跳转到收藏夹还是历史记录还是本地保存

    }
    fun moveToExtendActivity(choose: Int) {
        val intent = Intent( App.instant, ExtendActivity::class.java )
        intent.putExtra( "choose", choose )
        startActivity( intent )
    }
}
