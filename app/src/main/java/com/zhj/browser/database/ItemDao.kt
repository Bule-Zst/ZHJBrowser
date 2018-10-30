package com.zhj.browser.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ItemDao {
//    插入
    @Insert
    fun insert(item: Item )

//    删除
    @Delete
    fun delete(item: Item )

//    获取最多50条数据
    @Query( "select * from item order by id limit 50" )
    fun queryPart() : Array<Item>

//    获取全部数据
    @Query( "select * from item order by id" )
    fun queryAll() : Array<Item>

    @Query( "select * from item where category = :category order by id desc " )
    fun queryByCategory( category: Int ) : Array<Item>
}