package com.zhj.browser.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.zhj.browser.R
import com.zhj.browser.ui.adapter.FragmentPagerAdapterBuilder
import com.zhj.browser.ui.fragment.BookmarkFragment
import com.zhj.browser.ui.fragment.HistoryFragment
import com.zhj.browser.ui.fragment.SavedPageFragment
import kotlinx.android.synthetic.main.activity_fav.*

class FavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)
        initUI()
    }

    private fun initUI() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val pagerAdapter = FragmentPagerAdapterBuilder(this)
                .add(BookmarkFragment(),R.string.bookmark)
                .add(HistoryFragment(),R.string.history)
                .add(SavedPageFragment(),R.string.local)
                .build()
        viewPager.adapter = pagerAdapter
        favCatalogue.setupWithViewPager(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
