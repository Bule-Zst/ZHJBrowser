package com.zhj.browser.ui.activity

import android.os.Bundle
import android.app.Activity
import android.view.Gravity
import com.zhj.browser.R
import com.zhj.browser.ui.fragment.WebFragment
import com.zhj.browser.ui.popup.SearchPopup
import com.zhj.browser.ui.popup.WebToolMenu
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {

        fragmentManager.beginTransaction().add(R.id.webViewContainer,WebFragment()).commit()

        startSearchView.setOnClickListener {
            val searchView = SearchPopup(this)
            searchView.showAtLocation(mainActivityLayout,Gravity.TOP or Gravity.START,0,0)
        }

        toolMenu.setOnClickListener {
            showToolMenu()
        }
    }

    private fun showToolMenu(){
        val popToolView = WebToolMenu(this){tag ->
            when(tag){
                "collection" -> {startActivity<FavActivity>()}
                "fav" -> {}
                "fullScreen" -> {}
                "update" -> {}
                "save" -> {}
                "noImage" -> {}
                "about" -> {}
                "exit" -> {System.exit(0)}
            }
        }
        popToolView.showAtLocation(mainActivityLayout,Gravity.BOTTOM or Gravity.START,0,0)
    }
}
