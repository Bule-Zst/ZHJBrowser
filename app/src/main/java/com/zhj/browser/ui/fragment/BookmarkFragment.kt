package com.zhj.browser.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhj.browser.R
import com.zhj.browser.common.debug
import com.zhj.browser.common.info
import com.zhj.browser.database.AppDatabase
import com.zhj.browser.database.Item
import com.zhj.browser.database.toMsg

class BookmarkFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_bookmark,container,false)
        loadBookMark()
        return view
    }

    private fun loadBookMark() {
        //todo 加载书签
        var itemArray = emptyArray<Item>()
        AppDatabase.withAppDatabase { db ->
            itemArray = db.getDao().queryByCategory( Item.FAVOUR )
        }
        debug( itemArray.toMsg() )
    }
}