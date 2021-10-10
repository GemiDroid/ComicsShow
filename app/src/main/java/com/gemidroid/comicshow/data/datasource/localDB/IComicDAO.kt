package com.gemidroid.comicshow.data.datasource.localDB

import androidx.room.*
import com.gemidroid.comicshow.data.models.Comic

@Dao
interface IComicDAO {
    @Query("SELECT * FROM comics")
    fun getAll(): List<Comic>

    @Query("SELECT * FROM comics where num = :num")
    fun getComicByNumber(num: String): Comic

    @Insert
    fun insertAll(vararg comic: List<Comic>)

    @Delete
    fun delete(comic: Comic)

    @Update
    fun updateComic(vararg comics: Comic)
}