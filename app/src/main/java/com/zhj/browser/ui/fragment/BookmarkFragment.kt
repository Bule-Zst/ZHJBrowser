package com.zhj.browser.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhj.browser.R

class BookmarkFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_bookmark,container,false)
        loadBookMark()
        return view
    }

    private fun loadBookMark() {
        //todo 加载书签
    }
}