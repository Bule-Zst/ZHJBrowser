package com.zhj.browser.ui.popup

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.ImageView
import android.widget.PopupWindow
import com.zhj.browser.R
import com.zhj.browser.bean.WebToolMenuItem
import com.zhj.browser.common.PreferenceDict
import com.zhj.browser.storage.OpenPreference
import com.zhj.browser.tool.obtainColor
import com.zhj.browser.ui.adapter.WebToolMenuAdapter
import org.jetbrains.anko.find

class WebToolMenu(c : Context, onMenuItemClick : (tag : String)->Unit) : PopupWindow(){

    companion object {
        val webToolMenuList = listOf(
                WebToolMenuItem(R.drawable.ic_book,"书签/收藏夹","collection"),
                WebToolMenuItem(R.drawable.ic_star_border,"收藏","fav"),
                WebToolMenuItem(R.drawable.ic_crop_free,"全屏","fullScreen"),
                WebToolMenuItem(R.drawable.ic_sync,"刷新","update"),
                WebToolMenuItem(R.drawable.ic_get_app,"保存本地","save"),
                WebToolMenuItem(R.drawable.ic_layers_clear,"无图","noImage"),
                WebToolMenuItem(R.drawable.ic_aspect_ratio,"网页自适应","adaptive"),
                WebToolMenuItem(R.drawable.ic_exit_to_app,"退出","exit")
        )
    }

    init {
        isOutsideTouchable = true
        isFocusable = true
        setBackgroundDrawable(ColorDrawable(c.obtainColor(R.color.transparent)))
        width = WindowManager.LayoutParams.MATCH_PARENT
        height = WindowManager.LayoutParams.WRAP_CONTENT
        animationStyle = R.style.BottomMenuAnim

        val view = LayoutInflater.from(c).inflate(R.layout.popup_menu_web_tool,null)
        val menuListView = view.find<RecyclerView>(R.id.menuListView)
        menuListView.layoutManager = GridLayoutManager(c,4)
        val needTintList = MutableList(8){ index -> false }
        if(OpenPreference.getBoolean(PreferenceDict.isFullScreen,false)){
            needTintList[2] = true
        }
        if(OpenPreference.getBoolean(PreferenceDict.isNoImgMode,false)){
            needTintList[5] = true
        }
        if(OpenPreference.getBoolean(PreferenceDict.isAdaptive,false)){
            needTintList[6] = true
        }
        val adapter = WebToolMenuAdapter(c, webToolMenuList,needTintList)
        adapter.onItemClick = {tag ->
            onMenuItemClick(tag)
            dismiss()
        }
        menuListView.adapter = adapter
        contentView = view

        view.find<ImageView>(R.id.closeBtn).setOnClickListener {
            dismiss()
        }
    }
}