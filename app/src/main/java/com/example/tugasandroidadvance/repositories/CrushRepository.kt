package com.example.tugasandroidadvance.repositories

import androidx.lifecycle.LiveData
import com.example.tugasandroidadvance.database.DatabaseCrush
import com.example.tugasandroidadvance.entities.CrushEntity

class CrushRepository(val database: DatabaseCrush) {
    fun getAllCrushData(): LiveData<List<CrushEntity>> = database.dao().getAllCrushData()
    fun getCrushId(id:Int): LiveData<CrushEntity> = database.dao().getCrushId(id)
    suspend fun insertData(data:CrushEntity) = database.dao().insertData(data)
    suspend fun updateData(data:CrushEntity) = database.dao().updateData(data)
    suspend fun deleteData(id:CrushEntity) = database.dao().deleteData(id)
}