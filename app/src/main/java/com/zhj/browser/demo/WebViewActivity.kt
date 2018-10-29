package com.zhj.browser.demo

import android.app.Activity
import android.os.Bundle
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.WebView
import com.zhj.browser.R

//TSB的demo
class WebViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo)

        // 初始化X5内核
        QbSdk.initX5Environment( this, null )

        findViewById<WebView>( R.id.web_view ).loadUrl( "http://www.baidu.com" )
    }
}
