package com.zhj.browser.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhj.browser.R
import com.zhj.browser.common.IntentDict
import com.zhj.browser.common.info
import com.zhj.browser.database.AppDatabase
import com.zhj.browser.database.Item
import com.zhj.browser.ui.activity.MainActivity
import com.zhj.browser.ui.adapter.BookmarkAdapter
import kotlinx.android.synthetic.main.fragment_bookmark.*
import org.jetbrains.anko.intentFor

class BookmarkFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_bookmark,container,false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AppDatabase.withAppDatabase { db ->
            val itemArray = db.getItemDao().queryByCategory( Item.FAVOUR )
            info( itemArray.size.toString() )
            val adapter = BookmarkAdapter(activity!!,itemArray.toMutableList())
            adapter.onItemClick = {item ->
                val intent = Intent()
                intent.action = IntentDict.ACTION_SEARCH_URL
                intent.putExtra(IntentDict.URL,item.url)
                activity!!.sendBroadcast(intent)
                activity!!.finish()
            }
            bookmarkListView.adapter = adapter
        }
    }
}