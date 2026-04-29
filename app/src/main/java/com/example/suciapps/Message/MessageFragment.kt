package com.example.suciapps.Message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity // Tambahkan ini
import androidx.fragment.app.Fragment
import com.example.suciapps.databinding.ActivityMessageFragmentBinding

class MessageFragment : Fragment() {
    private var _binding: ActivityMessageFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityMessageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- TAMBAHKAN LOGIKA TOOLBAR & BACK DI SINI ---
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbarMessage) // Pasang toolbar ke Activity

        activity.supportActionBar?.apply {
            title = "Message" // Judul halaman
            setDisplayHomeAsUpEnabled(true) // Munculkan tombol panah back
        }

        // Event klik untuk tombol back
        binding.toolbarMessage.setNavigationOnClickListener {
            activity.onBackPressedDispatcher.onBackPressed()
        }
        // ----------------------------------------------
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}