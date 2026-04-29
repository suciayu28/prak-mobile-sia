package com.example.suciapps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.suciapps.databinding.ActivityMainBinding

// Import Activity - Pastikan path ini sesuai dengan struktur folder projectmu
import com.example.suciapps.Home.pertemuan_2.SecondActivity
import com.example.suciapps.Home.pertemuan_3.ThirdActivity
import com.example.suciapps.Home.pertemuan_4.FourthActivity
import com.example.suciapps.Home.pertemuan_5.FifthActivity
import com.example.suciapps.Home.pertemuan_7.SeventhActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // --- NAVIGASI TOMBOL PERTEMUAN ---

        binding.btnP2.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        binding.btnP3.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }

        binding.btnP4.setOnClickListener {
            startActivity(Intent(this, FourthActivity::class.java))
        }

        binding.btnP5.setOnClickListener {
            startActivity(Intent(this, FifthActivity::class.java))
        }

        binding.btnP7.setOnClickListener {
            startActivity(Intent(this, SeventhActivity::class.java))
        }

        // Tombol khusus PCR (Info Kampus) dengan pengiriman Data Intent
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // --- FITUR LOGOUT ---
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya") { dialog, _ ->
                    // Hapus session login
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    dialog.dismiss()

                    // Pindah ke AuthActivity dan hapus tumpukan activity (Stack)
                    val intent = Intent(this, AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}