package com.zhj.browser.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity( tableName = "favout_category" )
data class FavourCategory(
        @PrimaryKey( autoGenerate = true )
        var id: Int,
        var category: String
)