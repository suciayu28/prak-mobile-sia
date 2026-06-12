package com.example.suciapps.Data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.suciapps.Data.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<NoteEntity>

    @Insert
    suspend fun insert(note: NoteEntity)

    // 🛠️ TAMBAHAN: Fungsi aksi delete dari modul bab penanganan hapus item
    @Delete
    suspend fun delete(note: NoteEntity)
}