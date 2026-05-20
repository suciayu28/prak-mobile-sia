package com.example.suciapps.Home.pertemuan_10

import android.os.Bundle
import com.example.suciapps.R
import android.view.MenuItem // Tambahkan import ini
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.suciapps.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Set Judul Toolbar
        supportActionBar?.title = "Pertemuan 10"

        // 1. Inisialisasi Adapter
        val tabsAdapter = TenthTabsAdapter(this)

        // 2. Set adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 menggunakan Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Atur judul untuk setiap tab
            when (position) {
                0 -> {
                    tab.text = "Tab A"
                    //Tambah Icon
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)
                    //Tambah Badge Tanpa nomor (hanya titik)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }

                1 -> {
                    tab.text = "Tab B"
                    //Tambah Icon
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)
                    //Tambah Badge dengan nomor
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5
                }
                // 3. Tambahkan konfigurasi Tab C
                2 -> {
                    tab.text = "Produk"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home) // Ganti icon jika perlu
                }
            }
        }.attach()
    }

    // Perbaikan: Fungsi untuk menangani tombol Back di Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed() // Mengikuti alur sistem
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}