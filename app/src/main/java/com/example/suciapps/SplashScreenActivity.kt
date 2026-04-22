package com.example.suciapps

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Menggunakan lifecycleScope sesuai modul poin [1] sebelumnya
        lifecycleScope.launch {
            delay(2000) // Simulasi loading 2 detik

            // --- PERBAHARUI ALUR (Poin [4]) ---
            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            if (isLogin) {
                // Jika true -> arahkan ke MainActivity
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            } else {
                // Jika false -> arahkan ke AuthActivity
                startActivity(Intent(this@SplashScreenActivity, AuthActivity::class.java))
            }

            finish() // Tutup Splash agar tidak bisa di-back
        }
    }
}