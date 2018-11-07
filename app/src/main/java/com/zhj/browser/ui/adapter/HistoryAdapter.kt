package com.zhj.browser.ui.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.zhj.browser.R
import com.zhj.browser.database.AppDatabase
import com.zhj.browser.database.Item
import com.zhj.browser.ui.dialog.EditFavDialog
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.find
import org.jetbrains.anko.yesButton

class HistoryAdapter(val ctx: Context, var itemList: MutableList<Item>) : RecyclerView.Adapter<HistoryAdapter.WebRecordHolder>() {

    var onItemClick : (item : Item) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebRecordHolder {
        return WebRecordHolder(LayoutInflater.from(ctx).inflate(R.layout.item_web_record, parent, false))
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: WebRecordHolder, position: Int) {
        val bean = itemList[position]
        if (bean.bitmapPath.isNotBlank()) {
            holder.iconView.setImageBitmap(BitmapFactory.decodeFile(bean.bitmapPath))
        }
        holder.titleView.text = bean.title
        holder.urlView.text = bean.url
    }

    inner class WebRecordHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconView = view.find<ImageView>(R.id.webIconView)
        val titleView = view.find<TextView>(R.id.webTitleView)
        val urlView = view.find<TextView>(R.id.webUrlView)

        init {
            view.setOnClickListener {
                onItemClick(itemList[adapterPosition])
            }
            view.setOnLongClickListener {
                ctx.alert("are you sure want to delete the history"){
                    yesButton {
                        AppDatabase.withAppDatabase { db ->
                            db.getItemDao().delete(itemList[adapterPosition])
                            itemList.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                        }
                    }
                    cancelButton {  }
                }.show()

                return@setOnLongClickListener true
            }
        }
    }
}