package com.zhj.browser.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "match")
data class MatchUrl(
        @PrimaryKey( autoGenerate = true )
        var id: Int,
        var url: String
) {
    companion object {
        fun getDefault() = MatchUrl( 0, "" )
    }
}