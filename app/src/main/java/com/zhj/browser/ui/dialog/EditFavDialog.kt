package com.zhj.browser.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import com.zhj.browser.R
import com.zhj.browser.database.AppDatabase
import com.zhj.browser.database.FavourCategory
import com.zhj.browser.database.Item
import kotlinx.android.synthetic.main.dialog_edit_fav.*
import android.view.WindowManager

class EditFavDialog(private val c : Context,private val bookMark : Item) : AlertDialog(c) {

    var onFinish : (result : Item) -> Unit = {}
    var onDelete : () -> Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_edit_fav)
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        titleEditText.post { titleEditText.setText(bookMark.title) }
        categoryEditText.post {
            categoryEditText.setText(bookMark.favourCategory)
        }

        deleteBtn.setOnClickListener {
            onDelete()
            dismiss()
        }
        okBtn.setOnClickListener {
            val title = titleEditText.text.toString()
            val category = categoryEditText.text.toString()
            if(title.isNotBlank()){
                val result = bookMark
                result.title = title
                result.favourCategory = category
                AppDatabase.withAppDatabase { db ->
                    val dao = db.getFavoutCategoryDao()
                    if(dao.queryByCategory(category).isEmpty()){
                        dao.insert(FavourCategory(0,category))
                    }
                    db.getItemDao().update(result)
                }
                onFinish(result)
                dismiss()
            }
        }
    }
}