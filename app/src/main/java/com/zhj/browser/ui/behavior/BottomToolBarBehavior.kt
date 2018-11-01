package com.zhj.browser.ui.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.widget.NestedScrollView
import android.util.AttributeSet
import android.view.View

class BottomToolBarBehavior(c : Context,attrs : AttributeSet) : CoordinatorLayout.Behavior<View>(c,attrs){
    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        return dependency is NestedScrollView
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        return super.onDependentViewChanged(parent, child, dependency)
    }
}