package com.zhj.browser.ui.popup

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.widget.PopupWindow
import com.zhj.browser.R
import com.zhj.browser.tool.obtainColor

class WebToolMenu(c : Context) : PopupWindow(){
    init {
        isOutsideTouchable = true
        isFocusable = true
        setBackgroundDrawable(ColorDrawable(c.obtainColor(R.color.transparent)))
        //打开收藏/历史，收藏，退出，刷新，保存本地，无图，全屏，关于我们
    }
}