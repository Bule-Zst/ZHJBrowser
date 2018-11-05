package com.zhj.browser.ui.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class WebViewModel : ViewModel(){

    companion object {
        const val ACTION_SYNC = "sync"//同步
        const val ACTION_BACK = "back"//后退
        const val ACTION_FORWARD = "forward"//前进
        const val ACTION_HOME = "home"//回首页
        const val ACTION_SAVE = "save"//离线网页
        const val ACTION_FAVORITE = "favorite"//收藏网页
    }

    val action = MutableLiveData<String>()
    val isNoImgMode = MutableLiveData<Boolean>()
    val isAdaptive = MutableLiveData<Boolean>()
    val currentSearch = MutableLiveData<String>()
    val currentUrl = MutableLiveData<String>()
    val currentPath = MutableLiveData<String>()
}