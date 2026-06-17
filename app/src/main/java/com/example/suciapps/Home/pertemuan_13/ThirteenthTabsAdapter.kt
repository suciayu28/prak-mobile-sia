package com.example.suciapps.Home.pertemuan_13

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ThirteenthTabsAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    // Sesuai modul: set getItemCount = 3
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabCaptureFragment()
            1 -> TabScanFragment()
            2 -> TabQrcodeFragment()
            else -> TabCaptureFragment()
        }
    }
}