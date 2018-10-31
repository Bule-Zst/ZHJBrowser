package com.zhj.browser.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentPagerAdapter

class FragmentPagerAdapterBuilder(var a: FragmentActivity) {
    val fragmentSet = ArrayList<Fragment>()
    val titles = ArrayList<String>()

    fun add(fragment : Fragment,title : String): FragmentPagerAdapterBuilder {
        fragmentSet += fragment
        titles += title
        return this
    }

    fun add(fragment : Fragment,titleRes : Int): FragmentPagerAdapterBuilder {
        return this.add(fragment,a.resources.getString(titleRes))
    }

    fun build():FragmentPagerAdapter{
        return object : FragmentPagerAdapter(a.supportFragmentManager){
            override fun getCount(): Int {
                return fragmentSet.size
            }

            override fun getItem(position: Int): android.support.v4.app.Fragment {
                return fragmentSet[position]
            }

            override fun getPageTitle(position: Int): CharSequence {
                return titles[position]
            }
        }
    }
}