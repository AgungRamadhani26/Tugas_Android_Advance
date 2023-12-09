package com.example.tugasandroidadvance.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_crush")
data class CrushEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int = 0,
    @ColumnInfo(name = "nama")
    val name:String = "",
    @ColumnInfo(name = "deskripsi")
    val description:String = "",
    @ColumnInfo(name = "nohp")
    val phonenumber: String = ""
)
