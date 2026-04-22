package com.example.suciapps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.suciapps.databinding.ActivityMainBinding
import com.example.suciapps.pertemuan_4.FourthActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil SharedPreferences dengan nama yang sama seperti di AuthActivity
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // Tombol ke FourthActivity (Kode lama kamu tetap dipertahankan)
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // --- FITUR LOGOUT (Sesuai Modul Gambar [3]) ---
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    // 1. Ambil editor dan hapus semua data (isLogin & username)
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    // 2. Tutup dialog
                    dialog.dismiss()

                    // 3. Pindah kembali ke AuthActivity (Halaman Login)
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)

                    // 4. Tutup MainActivity agar tidak bisa di-back
                    finish()
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}