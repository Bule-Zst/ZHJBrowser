package com.zhj.browser.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.EditText
import com.zhj.browser.R
import org.jetbrains.anko.dip

class SearchEditText : EditText {

    //搜索图标大小
    private var searchIconWidth = 0

    //搜索图标点击事件
    var onSearchClick : ()->Unit = {}

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        //初始化显示图标
        searchIconWidth = dip(24)
        val searchDrawable = ContextCompat.getDrawable(context, R.drawable.ic_search)
        searchDrawable!!.setBounds(0,0,searchIconWidth,searchIconWidth)
        setCompoundDrawables(null,null,searchDrawable,null)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                //通过点击事件坐标判断是否触发搜索图标点击事件
                if(event.x in (width-searchIconWidth)..width){
                    onSearchClick()
                }
            }
        }
        return super.onTouchEvent(event)
    }
}