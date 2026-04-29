package com.example.suciapps.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity // Tambahkan ini
import androidx.fragment.app.Fragment
import com.example.suciapps.databinding.ActivityMoreFragmentBinding

class MoreFragment : Fragment() {

    private var _binding: ActivityMoreFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityMoreFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- TAMBAHKAN LOGIKA TOOLBAR & BACK DI SINI ---
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbarMore) // Menghubungkan ke ID di XML

        activity.supportActionBar?.apply {
            title = "More" // Judul di Toolbar
            setDisplayHomeAsUpEnabled(true) // Mengaktifkan tombol panah back
        }

        // Aksi ketika tombol back di toolbar ditekan
        binding.toolbarMore.setNavigationOnClickListener {
            activity.onBackPressedDispatcher.onBackPressed()
        }
        // ----------------------------------------------
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}