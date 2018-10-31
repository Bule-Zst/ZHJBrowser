package com.zhj.browser.tool

import android.content.Context
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat

fun Context.obtainColor(@ColorRes resId : Int) = ContextCompat.getColor(this,resId)
fun Any.print(){ println(this)}