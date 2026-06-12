package com.example.suciapps.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.suciapps.Data.dao.NoteDao
import com.example.suciapps.Data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class], // mendaftarkan entitas baru di sini
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    /*Tambahkan Dao baru disini */

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database_suci" // 🛠️ PERBAIKAN 1: Ganti nama database agar segar & bebas konflik
                )
                    .fallbackToDestructiveMigration() // 🛠️ PERBAIKAN 2: Reset otomatis jika ada error skema
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}