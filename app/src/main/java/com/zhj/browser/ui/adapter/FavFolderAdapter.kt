package com.zhj.browser.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zhj.browser.R
import com.zhj.browser.database.AppDatabase
import com.zhj.browser.database.FavourCategory
import org.jetbrains.anko.find

class FavFolderAdapter(private val c: Context, var folderList: MutableList<FavourCategory>) : RecyclerView.Adapter<FavFolderAdapter.FavFolderHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavFolderHolder {
        return FavFolderHolder(LayoutInflater.from(c).inflate(R.layout.item_fav_category,parent,false))
    }

    override fun getItemCount() = folderList.size

    override fun onBindViewHolder(holder: FavFolderHolder, position: Int) {
        holder.nameTextView.text = folderList[position].category
    }

    var onFolderClick : (folder : FavourCategory) -> Unit = {}

    inner class FavFolderHolder(view : View) : RecyclerView.ViewHolder(view){
        val nameTextView = view.find<TextView>(R.id.folderNameTextView)
        init {
            view.setOnClickListener {
                onFolderClick(folderList[adapterPosition])
            }
            view.setOnLongClickListener {
                AppDatabase.withAppDatabase { db ->
                    db.getFavoutCategoryDao().delete(folderList[adapterPosition])
                    folderList.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                }
                return@setOnLongClickListener true
            }
        }
    }
}