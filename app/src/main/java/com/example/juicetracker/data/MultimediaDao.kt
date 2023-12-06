package com.example.juicetracker.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.juicetracker.data.Model.Multimedia
import kotlinx.coroutines.flow.Flow

@Dao
interface MultimediaDao {
    @Insert
    fun insertMultimedia(multimedia: Multimedia)//: Long

    @Query("SELECT * FROM multimedia WHERE idNota=:idNota")
    fun getMultimedia(idNota: Int): Flow<List<Multimedia>>

    @Delete
    fun deleteMultimedia(multimedia: Multimedia)
}