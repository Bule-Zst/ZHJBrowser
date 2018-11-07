package com.zhj.browser.ui.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.zhj.browser.R
import com.zhj.browser.bean.WebToolMenuItem
import com.zhj.browser.tool.obtainColor
import org.jetbrains.anko.find

class WebToolMenuAdapter(val c: Context, val menuList: List<WebToolMenuItem>,val needTint : List<Boolean>) : RecyclerView.Adapter<WebToolMenuAdapter.WebToolMenuHolder>(){

    var onItemClick : (tag : String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebToolMenuHolder {
        return WebToolMenuHolder(LayoutInflater.from(c).inflate(R.layout.item_menu_web_tool,parent,false))
    }

    override fun getItemCount() = menuList.size

    override fun onBindViewHolder(holder: WebToolMenuHolder, position: Int) {
        val bean = menuList[position]
        holder.iconView.setImageResource(bean.icon)
        holder.titleView.text = bean.title
        if(needTint[position]){
            holder.iconView.imageTintList = ColorStateList.valueOf(c.obtainColor(R.color.menuBlue))
        }
    }

    inner class WebToolMenuHolder(view : View) : RecyclerView.ViewHolder(view){
        val iconView = view.find<ImageView>(R.id.iconView)
        val titleView = view.find<TextView>(R.id.titleView)
        init {
            view.setOnClickListener {
                onItemClick(menuList[adapterPosition].tag)
            }
        }
    }
}