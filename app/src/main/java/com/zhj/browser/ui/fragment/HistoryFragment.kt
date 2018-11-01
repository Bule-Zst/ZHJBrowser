package com.zhj.browser.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhj.browser.R
import com.zhj.browser.database.AppDatabase
import com.zhj.browser.database.Item

class HistoryFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_history,container,false)
        return view
    }

    private fun loadBookMark() {
        //todo 加载历史记录
        var itemArray = emptyArray<Item>()
        AppDatabase.withAppDatabase { db ->
            itemArray = db.getDao().queryByCategory( Item.HISTORY )
        }

    }
}