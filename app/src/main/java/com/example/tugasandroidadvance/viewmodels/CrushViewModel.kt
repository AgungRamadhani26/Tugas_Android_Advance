package com.example.tugasandroidadvance.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.tugasandroidadvance.entities.CrushEntity
import com.example.tugasandroidadvance.repositories.CrushRepository
import kotlinx.coroutines.launch

class CrushViewModel(val repo: CrushRepository) : ViewModel() {
    fun getAllCrushData() = repo.getAllCrushData()
    
    fun getCrushId(id:Int) = repo.getCrushId(id)

    fun insertData(data: CrushEntity) = viewModelScope.launch {
            repo.insertData(data)
    }

    fun updateData(data: CrushEntity) = viewModelScope.launch {
            repo.updateData(data)
    }

    fun deleteData(id: CrushEntity) = viewModelScope.launch {
            repo.deleteData(id)
    }
}