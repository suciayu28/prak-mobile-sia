package com.example.suciapps.Home.pertemuan_10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TenthTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // Jumlah total tab yang ada (Tab A dan Tab B)
    override fun getItemCount(): Int = 3

    // Menentukan Fragment mana yang akan ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabAFragment() // Memanggil Fragment konten A
            1 -> TabBFragment() // Memanggil Fragment konten B
            2 -> TabCFragment()// Memanggil Fragment konten C
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}