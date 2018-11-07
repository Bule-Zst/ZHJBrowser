package com.zhj.browser.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zhj.browser.R
import com.zhj.browser.database.AppDatabase
import com.zhj.browser.database.Item
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.find
import org.jetbrains.anko.yesButton

class SavedPageAdapter(val ctx: Context, val itemList: MutableList<Item>) : RecyclerView.Adapter<SavedPageAdapter.WebRecordHolder>() {

    var onItemClick : (item : Item) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebRecordHolder {
        return WebRecordHolder(LayoutInflater.from(ctx).inflate(R.layout.item_web_record, parent, false))
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: WebRecordHolder, position: Int) {
        val bean = itemList[position]
        holder.titleView.text = bean.title
        holder.urlView.text = bean.url
    }

    inner class WebRecordHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView = view.find<TextView>(R.id.webTitleView)
        val urlView = view.find<TextView>(R.id.webUrlView)

        init {
            view.setOnClickListener {
                onItemClick(itemList[adapterPosition])
            }
            view.setOnLongClickListener {
                ctx.alert(ctx.getString(R.string.tp_delete_web_item).replace("[val]",itemList[adapterPosition].title)){
                    yesButton {_ ->
                        itemList.removeAt(adapterPosition)
                        notifyItemRemoved(adapterPosition)
                        AppDatabase.withAppDatabase{db ->
                            db.getItemDao().delete(itemList[adapterPosition])
                        }
                    }
                    cancelButton {  }
                }.show()
                return@setOnLongClickListener true
            }
        }
    }
}