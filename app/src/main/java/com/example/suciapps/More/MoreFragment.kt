package com.example.suciapps.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter // Tambahkan import ini
import android.widget.Toast // Tambahkan import ini
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.suciapps.databinding.ActivityMoreFragmentBinding

class MoreFragment : Fragment() {

    private var _binding: ActivityMoreFragmentBinding? = null
    private val binding get() = _binding!!

    private val dataList = listOf(
        "Kotlin", "Java", "Python", "C++", "JavaScript",
        "Dart", "Swift", "Go", "Ruby", "R",
        "PHP", "C#", "TypeScript", "Shell", "SQL",
        "Perl", "Rust", "Scala", "Haskell", "Lua",
        "Erlang", "Prolog", "Assembly", "Objective-C", "VBA"
    )

    // TAMBAH: list baru sesuai modul
    private val dataListWithDesc = listOf(
        mapOf("title" to "Kotlin", "desc" to "Bahasa untuk Android modern"),
        mapOf("title" to "Java", "desc" to "Bahasa OOP yang populer"),
        mapOf("title" to "Python", "desc" to "Bahasa yang mudah dipahami")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityMoreFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Logika Toolbar
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbarMore)

        activity.supportActionBar?.apply {
            title = "More"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbarMore.setNavigationOnClickListener {
            activity.onBackPressedDispatcher.onBackPressed()
        }

        // 2. Logika ListView (Ubah menjadi SimpleAdapter sesuai modul)
        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )

        binding.listViewItems.adapter = adapter

        // TAMBAH: Aksi saat item di-list diklik
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]
            Toast.makeText(requireContext(), "Kamu memilih: $title ($desc)", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}