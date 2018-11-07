package com.zhj.browser.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import com.zhj.browser.R
import kotlinx.android.synthetic.main.dialog_edit_fav.*

class EditFavDialog(private val c : Context,private val rawStr : String) : AlertDialog(c) {

    var onFinish : (str : String) -> Unit = {}
    var onDelete : () -> Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_edit_fav)
        titleEditText.post { titleEditText.setText(rawStr) }

        deleteBtn.setOnClickListener {
            onDelete()
            dismiss()
        }
        okBtn.setOnClickListener {
            val result = titleEditText.text.toString()
            if(result.isNotBlank()){
                onFinish(result)
                dismiss()
            }
        }
    }
}