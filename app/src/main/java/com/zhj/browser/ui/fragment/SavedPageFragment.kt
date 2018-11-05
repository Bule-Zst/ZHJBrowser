package com.zhj.browser.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhj.browser.R
import com.zhj.browser.common.IntentDict
import com.zhj.browser.database.AppDatabase
import com.zhj.browser.database.Item
import com.zhj.browser.ui.adapter.SavedPageAdapter
import kotlinx.android.synthetic.main.fragment_saved_page.*

class SavedPageFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_saved_page,container,false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AppDatabase.withAppDatabase { db ->
            val itemList = db.getItemDao().queryByCategory(Item.LOCAL)
            val adapter = SavedPageAdapter(activity!!,itemList.toMutableList())
            adapter.onItemClick = {item ->
                val intent = Intent()
                intent.action = IntentDict.ACTION_SEARCH_URL
                intent.putExtra(IntentDict.URL,"file://" + item.bitmapPath)
                activity!!.sendBroadcast(intent)
                activity!!.finish()
            }
            savedPageListView.adapter = adapter
        }
    }
}