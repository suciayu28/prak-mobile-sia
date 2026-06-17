package com.example.suciapps.Home.pertemuan_13

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.suciapps.databinding.ActivityThirteenthBinding
// TAMBAHKAN IMPORT INI
import com.google.android.material.tabs.TabLayoutMediator

class ThirteenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menggunakan ViewBinding sesuai standar proyekmu
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Pasang Toolbar sebagai ActionBar activity
        setSupportActionBar(binding.toolbar)

        // 2. Aktifkan tombol Back (panah kiri) di Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // 3. Set judul halaman pada Toolbar
        supportActionBar?.title = "Pertemuan 13"

        // ============================================================
        // 🛠️ TAMBAHAN UNTUK MENGHUBUNGKAN ADAPTER, VIEW PAGER & TAB LAYOUT
        // ============================================================

        // Setup adapter untuk ViewPager2
        val tabsAdapter = ThirteenthTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        // Hubungkan TabLayout dengan ViewPager2 menggunakan TabLayoutMediator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Capture"
                1 -> "Scan"
                2 -> "QR Code"
                else -> null
            }
        }.attach()

        // ============================================================
    }

    // 4. Handle aksi ketika tombol back di Toolbar diklik
    override fun onSupportNavigateUp(): Boolean {
        // Menggunakan onBackPressedDispatcher (fitur Android terbaru yang aman)
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}