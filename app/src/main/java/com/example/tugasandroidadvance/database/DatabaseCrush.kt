package com.example.tugasandroidadvance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tugasandroidadvance.entities.CrushEntity
import com.example.tugasandroidadvance.fragment.HomeFragment

@Database(entities = [CrushEntity::class], version = 2)
abstract  class DatabaseCrush : RoomDatabase(){
    abstract fun dao(): Dao

    companion object{
        private const val DB_NAME = "database.db"
        @Volatile
        private var instance: DatabaseCrush? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext, DatabaseCrush::class.java, DB_NAME).fallbackToDestructiveMigration().build()
    }
}