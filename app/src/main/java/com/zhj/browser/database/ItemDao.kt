package com.zhj.browser.database

import android.arch.persistence.room.*

@Dao
interface ItemDao {
//    插入
//    返回值为插入的id
    @Insert
    fun insert(item: Item ) : Long

//    删除
    @Delete
    fun delete(item: Item )

    @Delete
    fun deleteAll( items: List<Item> )

//    获取最多50条数据
    @Query( "select * from item order by id limit 50" )
    fun queryPart() : List<Item>

//    获取全部数据
    @Query( "select * from item order by id" )
    fun queryAll() : List<Item>

    @Query( "select * from item where category = :category order by id desc " )
    fun queryByCategory( category: Int ) : List<Item>

    @Query( "select * from item where category = 0 and favourCategory = :favourCategory")
    fun queryBookmarkByCategory( favourCategory: String ) : List<Item>

    @Query( "select favourCategory from item where id = :id")
    fun queryFavourCategoryById( id: Int ): String

    @Update
    fun update( item: Item )
}