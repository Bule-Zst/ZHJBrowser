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
import com.zhj.browser.database.FavourCategory
import com.zhj.browser.database.Item
import com.zhj.browser.ui.adapter.BookmarkAdapter
import com.zhj.browser.ui.adapter.FavFolderAdapter
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment(){

    private var currentFolder = "root"
    private lateinit var folderAdapter : FavFolderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_bookmark,container,false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
        initFolder()
    }

    private fun initUI(){
        returnFolderBtn.setOnClickListener { returnFolder() }
    }

    private fun initFolder(){
        AppDatabase.withAppDatabase { db ->
            val folderList = db.getFavoutCategoryDao().queryAll()
            folderAdapter = FavFolderAdapter(activity!!,folderList.toMutableList())
            folderAdapter.onFolderClick = {folder ->
                openFolder(folder)
            }
            folderListView.adapter = folderAdapter
        }
    }

    private fun openFolder(folder : FavourCategory){
        folderListView.visibility = View.GONE
        favParent.visibility = View.VISIBLE
        AppDatabase.withAppDatabase { db ->
            val itemArray = db.getItemDao().queryBookmarkByCategory(folder.category)
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

    private fun returnFolder(){
        favParent.visibility = View.GONE
        folderListView.visibility = View.VISIBLE
        AppDatabase.withAppDatabase { db ->
            val folderList = db.getFavoutCategoryDao().queryAll()
            folderAdapter.folderList = folderList.toMutableList()
            folderAdapter.notifyDataSetChanged()
        }
    }
}