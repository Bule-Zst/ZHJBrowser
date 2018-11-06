package com.zhj.browser.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zhj.browser.R
import com.zhj.browser.database.MatchUrl
import org.jetbrains.anko.find

class MatchUrlAdapter(private val c: Context, var matchList: List<MatchUrl>) : RecyclerView.Adapter<MatchUrlAdapter.MatchUrlHolder>(){

    var onMatchItemClick : (url : String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchUrlHolder {
        return MatchUrlHolder(LayoutInflater.from(c).inflate(R.layout.item_match_url,parent,false))
    }

    override fun getItemCount() = matchList.size

    override fun onBindViewHolder(holder: MatchUrlHolder, position: Int) {
        holder.urlTextView.text = matchList[position].url
    }

    inner class MatchUrlHolder(view : View) : RecyclerView.ViewHolder(view){
        val urlTextView = view.find<TextView>(R.id.urlTextView)
        init {
            view.setOnClickListener {
                onMatchItemClick(matchList[adapterPosition].url)
            }
        }
    }
}