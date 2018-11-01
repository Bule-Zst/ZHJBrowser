package com.zhj.browser.ui.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class WebViewModel : ViewModel(){
    val isNoImgMode = MutableLiveData<Boolean>()
    val currentUrl = MutableLiveData<String>()
}