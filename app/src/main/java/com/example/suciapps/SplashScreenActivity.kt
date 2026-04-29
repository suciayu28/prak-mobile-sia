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

        lifecycleScope.launch {
            delay(2000)

            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            if (isLogin) {
                // --- PERBAIKAN DI SINI ---
                // Ubah MainActivity menjadi BaseActivity sesuai struktur Single Activity
                startActivity(Intent(this@SplashScreenActivity, BaseActivity::class.java))
            } else {
                // Jika belum login, ke AuthActivity sudah benar
                startActivity(Intent(this@SplashScreenActivity, AuthActivity::class.java))
            }

            finish()
        }
    }
}