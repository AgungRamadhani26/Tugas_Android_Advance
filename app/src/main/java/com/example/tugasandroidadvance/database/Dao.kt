package com.example.tugasandroidadvance.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.tugasandroidadvance.entities.CrushEntity

@Dao
interface Dao {
    @Query("SELECT * FROM tb_crush")
    fun getAllCrushData(): LiveData<List<CrushEntity>>

    @Query("SELECT * FROM tb_crush WHERE id = :id")
    fun getCrushId(id:Int): LiveData<CrushEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data:CrushEntity)

    @Update()
    suspend fun updateData(data: CrushEntity)

    @Delete()
    suspend fun deleteData(id: CrushEntity)
}