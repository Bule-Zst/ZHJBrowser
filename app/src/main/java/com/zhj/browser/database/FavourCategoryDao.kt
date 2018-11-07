package com.zhj.browser.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface FavourCategoryDao {
    @Query( "select * from favour_category" )
    fun queryAll(): List<FavourCategory>

    @Insert
    fun insert( favourCategory: FavourCategory ) : Long

    @Query( "select * from favour_category where category = :category" )
    fun queryByCategory( category: String ) : List<FavourCategory>

    @Delete
    fun delete( category: FavourCategory )
}