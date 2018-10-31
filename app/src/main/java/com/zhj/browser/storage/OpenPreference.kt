package com.zhj.browser.storage

import android.support.annotation.StringRes
import com.zhj.browser.App

object OpenPreference {
    private val sh = App.instance.getSharedPreferences("main",0)
    private val se = sh.edit()

    fun put(@StringRes key : Int, value : Int){ se.putInt(App.instance.getString(key),value);se.commit() }
    fun put(@StringRes key : Int,value : Float){ se.putFloat(App.instance.getString(key),value);se.commit() }
    fun put(@StringRes key : Int,value : String){ se.putString(App.instance.getString(key),value);se.commit() }
    fun put(@StringRes key : Int,value : Long){ se.putLong(App.instance.getString(key),value);se.commit() }
    fun put(@StringRes key : Int,value : Boolean){ se.putBoolean(App.instance.getString(key),value);se.commit() }

    fun getInt(@StringRes key : Int,dfValue : Int) = sh.getInt(App.instance.getString(key),dfValue)
    fun getFloat(@StringRes key : Int,dfValue : Float) = sh.getFloat(App.instance.getString(key),dfValue)
    fun getString(@StringRes key : Int,dfValue : String) = sh.getString(App.instance.getString(key),dfValue)
    fun getLong(@StringRes key : Int,dfValue : Long) = sh.getLong(App.instance.getString(key),dfValue)
    fun getBoolean(@StringRes key: Int, dfValue: Boolean) = sh.getBoolean(App.instance.getString(key), dfValue)

    fun put(key : String,value : Int){ se.putInt(key,value);se.commit() }
    fun put(key : String,value : Float){ se.putFloat(key,value);se.commit() }
    fun put(key : String,value : String){ se.putString(key,value);se.commit() }
    fun put(key : String,value : Long){ se.putLong(key,value);se.commit() }
    fun put(key : String,value : Boolean){ se.putBoolean(key,value);se.commit() }

    fun getInt(key : String,dfValue : Int) = sh.getInt(key,dfValue)
    fun getFloat(key : String,dfValue : Float) = sh.getFloat(key,dfValue)
    fun getString(key : String,dfValue : String) = sh.getString(key,dfValue)
    fun getLong(key : String,dfValue : Long) = sh.getLong(key,dfValue)
    fun getBoolean(key : String,dfValue : Boolean) = sh.getBoolean(key,dfValue)

}