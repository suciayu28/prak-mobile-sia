package com.example.suciapps.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.suciapps.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // 1. Inflate layout menggunakan View Binding
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root

        // 2. Ambil data berdasarkan posisi saat ini
        val data = messages[position]

        // 3. Load gambar profil menggunakan Glide
        Glide.with(context)
            .load(data.avatarUrl)
            .circleCrop() // Opsional: agar foto profil bulat
            .into(binding.avatarImg)

        // 4. Set teks nama dan pesan
        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        // 5. Event Listener untuk klik pada baris item
        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Pesan dari ${data.senderName}: ${data.messageText}",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}