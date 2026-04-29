package com.example.suciapps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.suciapps.Home.HomeFragment
import com.example.suciapps.Message.MessageFragment
import com.example.suciapps.More.MoreFragment
import com.example.suciapps.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        if (savedInstanceState == null) {
            // Untuk Home pertama kali, tidak perlu addToBackStack
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment(), false) // Home tidak perlu simpan backstack
                    true
                }
                R.id.message -> {
                    replaceFragment(MessageFragment(), true) // Message perlu simpan backstack
                    true
                }
                R.id.more -> {
                    replaceFragment(MoreFragment(), true) // More perlu simpan backstack
                    true
                }
                else -> false
            }
        }

        // Listener untuk sinkronisasi ikon Bottom Nav saat tombol Back ditekan
        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            if (currentFragment is HomeFragment) {
                binding.bottomNavigation.menu.findItem(R.id.home).isChecked = true
            }
        }
    }

    // Perbaikan fungsi replaceFragment agar mendukung BackStack
    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null) // Ini yang bikin tombol back berfungsi balik ke Home
        }

        transaction.commit()
    }
}