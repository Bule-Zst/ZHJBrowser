package com.zhj.browser.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface MatchUrlDao {
    @Insert
//    返回值为插入的id
    fun insert( matchUrl: MatchUrl ) : Long
    @Query( "select * from `match`" )
    fun queryAll(): List<MatchUrl>
    @Delete
    fun deleteById( matchUrl: MatchUrl )
    @Query( "select * from `match` where url like '%'||:find||'%'")
    fun queryByInput( find: String ) : List<MatchUrl>
}