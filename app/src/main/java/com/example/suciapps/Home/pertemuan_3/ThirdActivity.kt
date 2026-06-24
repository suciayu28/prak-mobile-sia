package com.example.suciapps.Home.pertemuan_3

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suciapps.R
import com.example.suciapps.databinding.ActivityThirdBinding
import com.example.suciapps.utils.NotificationHelper
import com.example.suciapps.utils.PermissionHelper
import com.example.suciapps.utils.ReminderHelper
import java.util.Calendar

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Third Activity"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        // Mempertahankan inisialisasi view dari kode asli Anda jika dibutuhkan oleh komponen lain
        val InputNoTujuan: EditText = findViewById(R.id.InputNoTujuan)
        val btnKirim: Button = findViewById(R.id.btnKirim)

        // BAGIAN YANG DIPERBARUI SESUAI MODUL LANGKAH 4 (REMINDER HELPER):
        binding.btnKirim.setOnClickListener {
            val noTujuan = binding.InputNoTujuan.text.toString()
            val intent = Intent(this, ThirdResultAcitvity::class.java)

            // Menutup/mengomentari pemanggilan notifikasi langsung sesuai instruksi Test Drive
            // NotificationHelper.showNotification(
            //     this,
            //     "Pesanan Anda",
            //     "Halo $noTujuan, Pesanan Anda Sedang Diproses",
            //     intent
            // )

            // Mengambil waktu saat ini dan menambahkannya sebesar 1 menit ke depan
            val calendar = Calendar.getInstance().apply {
                add(Calendar.MINUTE, 1)
            }

            // Menjadwalkan alarm/pengingat menggunakan ReminderHelper
            ReminderHelper.setReminder(
                context = this,
                hour = calendar.get(Calendar.HOUR_OF_DAY),
                minute = calendar.get(Calendar.MINUTE),
                title = "Reminder 1 Menit",
                message = "Halo $noTujuan, reminder ini muncul 1 menit setelah Anda menekan tombol kirim",
                targetActivity = ThirdResultAcitvity::class.java
            )

            Toast.makeText(this, "Silahkan tunggu 1 Menit untuk menerima Notifikasi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}