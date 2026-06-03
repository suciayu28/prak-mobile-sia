package com.example.suciapps.Message.tutorial

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suciapps.databinding.ActivityTutorialMessageBinding

class TutorialMessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Inisialisasi View Binding
        binding = ActivityTutorialMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Mengatur Toolbar (Mirip seperti implementasi di Home Fragment)
        // Pastikan di activity_tutorial_message.xml kamu memiliki widget Toolbar dengan id 'toolbar'
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Tutorial Fitur Pesan"
            setDisplayHomeAsUpEnabled(true) // Menambahkan tombol kembali (panah kiri) di toolbar
        }

        // 3. Menangani Window Insets agar tidak terpotong status bar
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 4. Setup List Fragment untuk ViewPager2
        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )

        // 5. Pasang Adapter ke ViewPager2
        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialMessageViewPager.adapter = adapter
        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)
    }

    // Aksi ketika tombol kembali (panah kiri) di Toolbar diklik
    override fun onSupportNavigateUp(): Boolean {
        finish() // Menutup activity tutorial dan kembali ke fragment Message
        return true
    }
}