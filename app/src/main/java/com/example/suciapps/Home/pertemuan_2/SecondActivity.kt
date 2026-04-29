package com.example.suciapps.Home.pertemuan_2

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suciapps.R
import com.example.suciapps.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    // Menggunakan ViewBinding agar lebih rapi dan aman
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi binding
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar dan Tombol Back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Pertemuan 2"
            subtitle = "Toast & Log"
            setDisplayHomeAsUpEnabled(true) // Memunculkan tombol panah kembali
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 2. Logika Tombol Submit
        binding.btnSubmit.setOnClickListener {
            val nama = binding.inputNama.text.toString()

            // Log untuk memantau di Logcat
            Log.e("Klik btnSubmit", "Tombol berhasil di tekan. Isi dari inputNama = $nama")

            // Toast untuk memberikan feedback ke user
            Toast.makeText(this, "Halo $nama, Anda menekan tombol Submit", Toast.LENGTH_SHORT).show()
        }
    }

    // 3. Fungsi agar tombol Back di Toolbar berfungsi
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish() // Menutup activity ini dan kembali ke MainActivity
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}