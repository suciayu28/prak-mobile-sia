package com.example.suciapps.Message

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.suciapps.Message.tutorial.TutorialMessageActivity
import com.example.suciapps.databinding.ActivityMessageFragmentBinding
import  com.example.suciapps.R
class MessageFragment : Fragment() {
    private var _binding: ActivityMessageFragmentBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Alya", "Halo! Apa kabar?", "https://api.dicebear.com/7.x/avataaars/png?seed=Alya"),
        MessageModel("Budi", "Sudah makan?", "https://api.dicebear.com/7.x/avataaars/png?seed=Budi"),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://api.dicebear.com/7.x/avataaars/png?seed=Citra"),
        MessageModel("Dika", "Besok kita rapat jam 9", "https://api.dicebear.com/7.x/avataaars/png?seed=Dika"),
        MessageModel("Eka", "Nice job kemarin!", "https://api.dicebear.com/7.x/avataaars/png?seed=Eka"),
        MessageModel("Fajar", "Lagi ngapain?", "https://api.dicebear.com/7.x/avataaars/png?seed=Fajar"),
        MessageModel("Gita", "Boleh minta tolong?", "https://api.dicebear.com/7.x/avataaars/png?seed=Gita"),
        MessageModel("Hana", "Lihat email ya", "https://api.dicebear.com/7.x/avataaars/png?seed=Hana"),
        MessageModel("Irfan", "Oke noted", "https://api.dicebear.com/7.x/avataaars/png?seed=Irfan"),
        MessageModel("Joko", "Sampai jumpa besok", "https://api.dicebear.com/7.x/avataaars/png?seed=Joko")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityMessageFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- LOGIKA TOOLBAR & BACK ---
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(binding.toolbarMessage) // Pasang toolbar ke Activity

        activity.supportActionBar?.apply {
            title = "Message" // Judul halaman
            setDisplayHomeAsUpEnabled(true) // Munculkan tombol panah back
        }

        // Aktifkan Option Menu di Fragment (Perintah Modul Langkah 4)
        setHasOptionsMenu(true)

        // Event klik untuk tombol back
        binding.toolbarMessage.setNavigationOnClickListener {
            activity.onBackPressedDispatcher.onBackPressed()
        }
        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
        // ----------------------------------------------
    }

    // --- TAMBAHAN KODE DARI PERINTAH MODUL (onCreateOptionsMenu & onOptionsItemSelected) ---

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_tutorial -> {
                val intent = Intent(requireContext(), TutorialMessageActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // -------------------------------------------------------------------------------------

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}