package com.zhj.browser.ui.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.view.View

class BottomToolBarBehavior(c: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>(c, attrs) {

    private var oldY = -1f

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        return dependency is NestedScrollView
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        if (dependency == null) return super.onDependentViewChanged(parent, child, dependency)
        println("gety == ${dependency.y}")
        if (oldY == -1f) {
            oldY = dependency.y
        } else if (dependency.y - oldY > 0) {
            child!!.visibility = View.GONE
        } else if (dependency.y - oldY < 0) {
            child!!.visibility = View.VISIBLE
        }
        return true
    }
}