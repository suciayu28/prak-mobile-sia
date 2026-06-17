package com.example.suciapps.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suciapps.AuthActivity
import com.example.suciapps.Data.api.PhotoApiClient
import com.example.suciapps.Home.pertemuan_2.SecondActivity
import com.example.suciapps.Home.pertemuan_3.ThirdActivity
import com.example.suciapps.Home.pertemuan_4.FourthActivity
import com.example.suciapps.Home.pertemuan_5.FifthActivity
import com.example.suciapps.Home.pertemuan_7.SeventhActivity
import com.example.suciapps.Home.pertemuan_9.NinthActivity
import com.example.suciapps.Home.pertemuan_10.TenthActivity
import com.example.suciapps.Home.pertemuan_13.ThirteenthActivity
import com.example.suciapps.Home.photo.PhotoAdapter
import com.example.suciapps.databinding.ActivityHomeFragmentBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: ActivityHomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityHomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengatur Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Home"

        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // --- Listener Klik Dashboard (CardView) ---

        binding.btnP2.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }
        binding.btnP3.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }
        binding.btnP4.setOnClickListener {
            startActivity(Intent(requireContext(), FourthActivity::class.java))
        }
        binding.btnP5.setOnClickListener {
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }
        binding.btnP7.setOnClickListener {
            startActivity(Intent(requireContext(), SeventhActivity::class.java))
        }

        // Navigasi ke Pertemuan 9 (Sesuai ID CardView di XML)
        binding.btnP9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }
        // Tambahkan baris ini di dalam fungsi onViewCreated
        binding.btnP10.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
        }

        // Klik untuk tombol Pertemuan 13
        binding.btnP13.setOnClickListener {
            val intent = Intent(requireContext(), ThirteenthActivity::class.java)
            startActivity(intent)
        }

        binding.btnToFourth.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java).apply {
                putExtra("name", "Politeknik Caltex Riau")
                putExtra("from", "Rumbai")
                putExtra("age", 25)
            }
            startActivity(intent)
        }

        // 🛠️ PANGGIL FUNGSI LOAD PHOTO DI SINI (Sesuai Modul Langkah 6)
        loadPhoto()

        // Tombol Logout
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya") { _, _ ->
                    sharedPref.edit().clear().apply()
                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    // 🛠️ TAMBAHAN FUNGSI LOADPHOTO (Sesuai Modul Langkah 6)
    private fun loadPhoto() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter

                /** List Tampil Vertical **/
                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())

                /** List Tampil Horizontal (Opsional jika ingin dipakai tinggal hapus tanda //) **/
                // binding.rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                /** List Tampil Grid (Opsional jika ingin dipakai tinggal hapus tanda //) **/
                // binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 2)

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}