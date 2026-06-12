package com.example.suciapps.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.suciapps.Data.AppDatabase
import com.example.suciapps.Data.entity.NoteEntity
import com.example.suciapps.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {

    // 1. Deklarasi View Binding dan Database Room sesuai modul
    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inisialisasi View Binding
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. Inisialisasi Database Instance
        db = AppDatabase.getInstance(this)

        // 4. Aksi Klik Tombol Simpan untuk Menyimpan Catatan Baru (image_af1ba0.png)
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()

            // Validasi sederhana agar input tidak kosong
            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Judul dan isi tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Eksekusi penyimpanan menggunakan coroutine lifecycleScope (image_af1b7e.png)
            lifecycleScope.launch {
                val note = NoteEntity(
                    title = title,
                    content = content,
                    createdAt = System.currentTimeMillis()
                )

                db.noteDao().insert(note) // Menjalankan Query Insert Room Database

                // Menampilkan feedback sukses dan menutup halaman form
                Toast.makeText(this@NoteFormActivity, "Catatan berhasil disimpan", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}