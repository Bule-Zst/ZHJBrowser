package com.zhj.browser.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhj.browser.R
import com.zhj.browser.common.IntentDict
import com.zhj.browser.database.AppDatabase
import com.zhj.browser.database.Item
import com.zhj.browser.ui.activity.MainActivity
import com.zhj.browser.ui.adapter.BookmarkAdapter
import kotlinx.android.synthetic.main.fragment_history.*
import org.jetbrains.anko.intentFor

class HistoryFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_history,container,false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AppDatabase.withAppDatabase { db ->
            val itemList = db.getItemDao().queryByCategory( Item.HISTORY )
            val adapter = BookmarkAdapter(activity!!,itemList.toMutableList())
            adapter.onItemClick = {item ->
                startActivity(activity!!.intentFor<MainActivity>(IntentDict.URL to item.url))
            }
            historyListView.adapter = adapter
        }
    }
}