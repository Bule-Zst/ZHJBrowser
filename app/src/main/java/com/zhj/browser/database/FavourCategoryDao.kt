package com.zhj.browser.database

import android.arch.persistence.room.Query

interface FavourCategoryDao {
    @Query( "select * from favout_category" )
    fun queryAll(): List<FavourCategory>
}