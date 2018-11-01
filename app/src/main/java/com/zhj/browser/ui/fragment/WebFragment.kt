package com.zhj.browser.ui.fragment

import android.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.FragmentActivity
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
import com.zhj.browser.ui.viewModel.WebViewModel
import kotlinx.android.synthetic.main.fragment_web.*
import org.jetbrains.anko.toast

class WebFragment : Fragment(){

    private lateinit var webViewModel: WebViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_web,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        webViewModel = ViewModelProviders.of(activity as FragmentActivity).get(WebViewModel::class.java)
        startObserve()
        addListen()
        mWebView.loadUrl( "https://www.baidu.com/" )
    }

    private fun startObserve(){
        webViewModel.isNoImgMode.observeForever { isNoImgMode : Boolean? ->
            if(isNoImgMode == null)return@observeForever
            //操作
        }
        webViewModel.action.observeForever { action : String? ->
            if (action == null) {
                return@observeForever
            }
            when(action){
                WebViewModel.ACTION_SAVE -> {}
                WebViewModel.ACTION_SYNC -> {}
                WebViewModel.ACTION_FAVORITE -> {}
                WebViewModel.ACTION_BACK -> {}
                WebViewModel.ACTION_FORWARD -> {}
                WebViewModel.ACTION_HOME -> {}
            }
        }
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

    private fun addBookMark() {
        AppDatabase.withAppDatabase { db ->
            db.getDao().insert( with( Item.getDefault() ) {
                this.title = mWebView.title
                this.url = mWebView.url
                this.bitmapPath = if( mWebView.favicon == null ) "" else {
                    BitmapTool.saveBitmap( mWebView.favicon )!!
                }
                this.category = Item.FAVOUR
                this
            })
            toast( "add bookmark success" )
        }
    }
}