package com.zhj.browser.ui.popup

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import com.zhj.browser.R
import com.zhj.browser.database.AppDatabase
import com.zhj.browser.tool.obtainColor
import com.zhj.browser.ui.adapter.MatchUrlAdapter
import com.zhj.browser.ui.view.SearchEditText
import org.jetbrains.anko.find

class SearchPopup(a : Activity) : PopupWindow(a) {

    var onSearchStart : (content : String) -> Unit = {}
    private lateinit var adapter : MatchUrlAdapter

    init {
        isOutsideTouchable = true
        isFocusable = true
        setBackgroundDrawable(ColorDrawable(a.obtainColor(R.color.transparentBlack)))
        width = WindowManager.LayoutParams.MATCH_PARENT
        height = WindowManager.LayoutParams.MATCH_PARENT

        val view = LayoutInflater.from(a).inflate(R.layout.popup_search,null)
        view.find<ViewGroup>(R.id.searchLayout).setOnClickListener {
            dismiss()
        }
        val matchListView = view.find<RecyclerView>(R.id.matchListView)
        AppDatabase.withAppDatabase { db ->
            val urlList = db.getMatchUrlDao().queryAll()
            adapter = MatchUrlAdapter(a,urlList)
            adapter.onMatchItemClick = {url ->
                onSearchStart(url)
                dismiss()
            }
            matchListView.adapter = adapter
        }

        val searchEditText = view.find<SearchEditText>(R.id.searchEditText)
        searchEditText.onSearchClick = {
            val content = searchEditText.text.toString()
            if(content.isNotBlank()){
                onSearchStart(content)
                dismiss()
            }
        }
        searchEditText.setOnKeyListener { _, keyCode, keyEvent ->
            if(keyCode == KeyEvent.KEYCODE_ENTER){
                val content = searchEditText.text.toString()
                if(content.isNotBlank()){
                    onSearchStart(content)
                    dismiss()
                }
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
        searchEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(editable : Editable?) {
                val urlFrag = editable?.toString() ?: return
                AppDatabase.withAppDatabase { db ->
                    val list = db.getMatchUrlDao().queryByInput( urlFrag )
                    if(list.isNotEmpty()){
                        adapter.matchList = list
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        })
        contentView = view
    }
}