package com.zhj.browser.ui.fragment

import android.app.Fragment
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import com.zhj.browser.App
import com.zhj.browser.R
import com.zhj.browser.database.AppDatabase
import com.zhj.browser.database.Item
import com.zhj.browser.extend.ExtendActivity
import com.zhj.browser.tool.BitmapTool
import kotlinx.android.synthetic.main.fragment_web.*

class WebFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_web,container,false)
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
        addListen()
        mWebView.loadUrl( "https://www.baidu.com/s?ie=UTF-8&wd=android%20%E4%BF%9D%E5%AD%98bitmap" )
    }

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
        val intent = Intent( App.instance, ExtendActivity::class.java )
        intent.putExtra( "choose", choose )
        startActivity( intent )
    }
}