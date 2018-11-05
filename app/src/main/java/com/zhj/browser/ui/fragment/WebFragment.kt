package com.zhj.browser.ui.fragment

import android.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tencent.smtt.sdk.ValueCallback
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import com.zhj.browser.R
import com.zhj.browser.common.info
import com.zhj.browser.database.AppDatabase
import com.zhj.browser.database.Item
import com.zhj.browser.database.MatchUrl
import com.zhj.browser.database.MatchUrlDao
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
        webViewModel.currentUrl.observeForever { url ->
            mWebView.loadUrl(url)
        }
        webViewModel.currentSearch.observeForever { word ->
            if(word != null && word.isNotBlank()){
                mWebView.loadUrl("https://www.baidu.com/s?ie=UTF-8&wd=" + word)
            }
        }
        webViewModel.isNoImgMode.observeForever { isNoImgMode : Boolean? ->
            if(isNoImgMode == null)return@observeForever
            else if(isNoImgMode==true)
            {
                mWebView.settings.blockNetworkImage = true
                toast("Set noImage")
            }
            else{
                mWebView.settings.blockNetworkImage = false
                toast("Set haveImage")
            }
        }
        webViewModel.isAdaptive.observeForever { isAdaptive: Boolean? ->
            if( isAdaptive == null ) {
                return@observeForever
            }
            if(isAdaptive) {
                openAdaptiveMode()
            } else {
                closeAdaptiveMode()
            }
        }
        webViewModel.action.observeForever { action : String? ->
            if (action == null) {
                return@observeForever
            }
            when(action){
                WebViewModel.ACTION_SAVE -> {
                    savepage()
                }
                WebViewModel.ACTION_SYNC -> {
                    if(webViewModel.currentUrl.value?.isNotBlank()?:false){
                        mWebView.reload()
                    }
                }
                WebViewModel.ACTION_FAVORITE -> {
                    addBookMark()
                }
                WebViewModel.ACTION_BACK -> {
                    info( "go back" )
                    mWebView.goBack()
                }
                WebViewModel.ACTION_FORWARD -> {
                    mWebView.goForward()
                }
                WebViewModel.ACTION_HOME -> {
                    mWebView.loadUrl("https://www.baidu.com/")
                }
            }
        }
    }

    private fun openAdaptiveMode() {
        val settings = mWebView.settings
        settings.loadWithOverviewMode = true
        toast( "adaptive mode is opend" )
    }

    private fun closeAdaptiveMode() {
        val settings = mWebView.settings
        settings.loadWithOverviewMode = false
        toast( "adaptive mode is closed" )
    }

    fun addListen() {
        mWebView.webViewClient = object : WebViewClient() {
            private var if_load: Boolean = false
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished( view, url )

                info( "on page finish" )

                if( !if_load ) {
                    return;
                }

                val bitmap = view.favicon
                val title = view.title
                val mUrl = view.url
                info( String.format( "url is %s", mUrl ) )
                val bitmapPath =  if( bitmap == null ) "" else { BitmapTool.saveBitmap( bitmap ) }

//                将数据添加到item表中
                AppDatabase.withAppDatabase {  db ->
                    val id = db.getItemDao().insert( with( Item.getDefault() ) {
                        this.bitmapPath = if( bitmapPath == null ) "" else { bitmapPath }
                        this.url = mUrl
                        this.title = title
                        this.category = 1
                        this
                    })
                    info( String.format( "insert into item success, id = %d", id ) )
                }

//                将数据添加到match表中
                AppDatabase.withAppDatabase { db ->
                    val id = db.getMatchUrlDao().insert( with( MatchUrl.getDefault() ) {
                        this.url = mUrl
                        this
                    })
                    info( String.format( "insert into match success, id is %d", id ) )
                }

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
        info( "add book mark" )
        AppDatabase.withAppDatabase { db ->
            val id = db.getItemDao().insert( with( Item.getDefault() ) {
                this.title = mWebView.title
                this.url = mWebView.url
                this.bitmapPath = if( mWebView.favicon == null ) "" else {
                    BitmapTool.saveBitmap( mWebView.favicon )!!
                }
                this.category = Item.FAVOUR
                this
            })
            info( String.format( "insert into item success, id is %d", id ) )
            toast( "add bookmark success" )
        }
    }

    fun savepage(){
        val filename:String = mWebView.title
        val url:String = mWebView.url
        val file_path:String = activity.filesDir.toString()+"/"+filename+".mnt"
        mWebView.saveWebArchive(file_path,false, object :ValueCallback<String>
        {
            override fun onReceiveValue(p0: String?) {
                if(p0==null)
                {
                    toast("离线网页保存失败!")
                }
                else
                {
                    AppDatabase.withAppDatabase { db ->
                        db.getItemDao().insert( with( Item.getDefault() ) {
                            this.bitmapPath = file_path
                            this.title = filename
                            this.url = url
                            this.category = Item.LOCAL
                            this
                        })
                    }
                }
            }
        })
    }
}