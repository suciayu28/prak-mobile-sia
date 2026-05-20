package com.example.suciapps.Home.pertemuan_10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.suciapps.databinding.ActivityTabCfragmentBinding // Pastikan nama binding sesuai XML kamu

class TabCFragment : Fragment() {

    // Inisialisasi View Binding untuk Fragment
    private var _binding: ActivityTabCfragmentBinding? = null
    private val binding get() = _binding!!

    private val productList = listOf(
        ProductModel("Sepatu Running Nike", "Rp 850.000", "https://picsum.photos/seed/shoe1/400/300"),
        ProductModel("Kemeja Flannel", "Rp 320.000", "https://picsum.photos/seed/shirt1/400/300"),
        ProductModel("Tas Ransel Laptop", "Rp 450.000", "https://picsum.photos/seed/bag1/400/300"),
        ProductModel("Jam Tangan Casio", "Rp 1.200.000", "https://picsum.photos/seed/watch1/400/300"),
        ProductModel("Headphone Sony", "Rp 1.500.000", "https://picsum.photos/seed/audio1/400/300"),
        ProductModel("Kaos Polos Premium", "Rp 150.000", "https://picsum.photos/seed/tshirt1/400/300"),
        ProductModel("Celana Jogger", "Rp 280.000", "https://picsum.photos/seed/pants1/400/300"),
        ProductModel("Sneakers Adidas", "Rp 950.000", "https://picsum.photos/seed/shoe2/400/300"),
        ProductModel("Dompet Kulit", "Rp 220.000", "https://picsum.photos/seed/wallet1/400/300"),
        ProductModel("Topi Baseball", "Rp 120.000", "https://picsum.photos/seed/hat1/400/300")
        // ... tambahkan data lainnya di sini
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityTabCfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi Adapter
        val adapter = ProductAdapter(productList) { selectedItem ->
            Toast.makeText(requireContext(), "Anda memilih ${selectedItem.name}", Toast.LENGTH_SHORT).show()
        }

        // Setup RecyclerView
        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}