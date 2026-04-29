package com.example.suciapps.Home.pertemuan_7

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
// Pastikan import R dan Binding sudah benar
import com.example.suciapps.databinding.ActivitySeventhBinding

class SeventhActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeventhBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeventhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar (Agar tombol Back <- muncul)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Activity Seventh"
            subtitle = "Penerapan Fragment"
        }

        // 2. Munculkan Fragment Pertama secara default
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, SatuFragment())
                .commit()
        }

        // 3. Listener Tombol untuk navigasi ke Fragment masing-masing
        binding.btnFragment1.setOnClickListener { replaceFragment(SatuFragment()) }
        binding.btnFragment2.setOnClickListener { replaceFragment(DuaFragment()) }
        binding.btnFragment3.setOnClickListener { replaceFragment(TigaFragment()) }
    }

    // Fungsi Helper untuk pindah Fragment
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .addToBackStack(null) // Agar bisa back antar fragment
            .commit()
    }

    // 4. Fungsi Klik Tombol Back (<-) di Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Harus menggunakan android.R.id.home
        if (item.itemId == android.R.id.home) {
            finish() // Kembali ke MainActivity
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}