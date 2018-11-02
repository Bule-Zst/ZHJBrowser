package com.zhj.browser.ui.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.FragmentActivity
import android.view.Gravity
import com.zhj.browser.R
import com.zhj.browser.common.IntentDict
import com.zhj.browser.common.PreferenceDict
import com.zhj.browser.storage.OpenPreference
import com.zhj.browser.ui.fragment.WebFragment
import com.zhj.browser.ui.popup.SearchPopup
import com.zhj.browser.ui.popup.WebToolMenu
import com.zhj.browser.ui.viewModel.WebViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.startActivity

class MainActivity : FragmentActivity() {

    private var isFullScreen = false
    private lateinit var webViewModel: WebViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webViewModel = ViewModelProviders.of(this).get(WebViewModel::class.java)
        initData()
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

    private fun initData(){
        webViewModel.currentUrl.value = intent.extras[IntentDict.URL] as String
        isFullScreen = OpenPreference.getBoolean(PreferenceDict.isFullScreen,false)
        webViewModel.isNoImgMode.value = OpenPreference.getBoolean(PreferenceDict.isNoImgMode,false)
    }

    private fun toggleFullScreen(){
        val lp = webViewContainer.layoutParams as CoordinatorLayout.LayoutParams
        if(isFullScreen){
            lp.bottomMargin = dip(48)
        }else{
            lp.bottomMargin = 0
        }
        webViewContainer.layoutParams = lp
    }

    private fun showToolMenu(){
        val popToolView = WebToolMenu(this){tag ->
            when(tag){
                "collection" -> {startActivity<FavActivity>()}
                "fav" -> {webViewModel.action.value = WebViewModel.ACTION_FAVORITE}
                "fullScreen" -> {toggleFullScreen()}
                "update" -> {webViewModel.action.value = WebViewModel.ACTION_SYNC}
                "save" -> {webViewModel.action.value = WebViewModel.ACTION_SAVE}
                "noImage" -> {
                    var isNoImgMode = webViewModel.isNoImgMode.value?:false
                    isNoImgMode = !isNoImgMode
                    OpenPreference.put(PreferenceDict.isNoImgMode,isNoImgMode)
                    webViewModel.isNoImgMode.value = isNoImgMode
                }
                "about" -> {}
                "exit" -> {System.exit(0)}
            }
        }
        popToolView.showAtLocation(mainActivityLayout,Gravity.BOTTOM or Gravity.START,0,0)
    }
}
