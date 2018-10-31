package com.zhj.browser.ui.activity

import android.os.Bundle
import android.app.Activity
import android.view.Gravity
import com.zhj.browser.R
import com.zhj.browser.tool.WindowUtil
import com.zhj.browser.ui.fragment.WebFragment
import com.zhj.browser.ui.popup.WebToolMenu
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {

        fragmentManager.beginTransaction().replace(R.id.webViewContainer,WebFragment()).commit()

        toolMenu.setOnClickListener {
            val popToolView = WebToolMenu(this)
            val viewMargin = 32
            val screenSize = WindowUtil.getScreenSize(this)
            popToolView.width = screenSize.width - 2*viewMargin
            popToolView.showAtLocation(mainActivityLayout,Gravity.BOTTOM or Gravity.LEFT,viewMargin,viewMargin)
        }
    }

}
