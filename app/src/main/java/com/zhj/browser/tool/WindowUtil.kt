package com.zhj.browser.tool

import android.app.Activity
import android.util.DisplayMetrics
import android.util.Size

object WindowUtil{
    fun getScreenWidth(a : Activity): Int {
        val metric = DisplayMetrics()
        a.windowManager.defaultDisplay.getMetrics(metric)
        return metric.widthPixels
    }

    fun getScreenHeight(a : Activity): Int {
        val metric = DisplayMetrics()
        a.windowManager.defaultDisplay.getMetrics(metric)
        return metric.heightPixels
    }

    fun getScreenSize(a : Activity): Size {
        val metric = DisplayMetrics()
        a.windowManager.defaultDisplay.getMetrics(metric)
        return Size(metric.widthPixels,metric.heightPixels)
    }
}