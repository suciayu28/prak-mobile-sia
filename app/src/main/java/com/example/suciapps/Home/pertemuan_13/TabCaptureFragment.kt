package com.example.suciapps.Home.pertemuan_13

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.suciapps.databinding.FragmentTabCaptureBinding
import com.example.suciapps.utils.PermissionHelper
import java.io.File

class TabCaptureFragment : Fragment() {

    private var _binding: FragmentTabCaptureBinding? = null
    private val binding get() = _binding!!

    // Variabel sesuai modul langkah 3
    private var currentPhotoUri: Uri? = null

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            currentPhotoUri?.let { uri ->
                binding.ivCapturedImage.setImageURI(uri) //
                context?.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri)) //
            }
        }
    }

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            openCamera() //
        } else {
            Toast.makeText(context, "Izin kamera diperlukan", Toast.LENGTH_SHORT).show() //
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCaptureBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Function onViewCreated Diperbarui Sesuai Modul Langkah 4 (Menggunakan PermissionHelper)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCapture.setOnClickListener {
            if (!PermissionHelper.hasPermission(requireActivity(), Manifest.permission.CAMERA)) {
                PermissionHelper.requestPermission(
                    permissionLauncher,
                    Manifest.permission.CAMERA
                )
            } else {
                openCamera()
            }
        }
    }

    private fun openCamera() { //
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) //

        // Memanggil fungsi pembuatan URI baru yang aman untuk API 36
        currentPhotoUri = createGalleryPhotoUri()

        intent.putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri) //
        cameraLauncher.launch(intent) //
    }

    // 🛠️ PERBAIKAN LOGIKA UTAMA: Menggunakan FileProvider agar tidak crash di Android API Tinggi / Modern
    private fun createGalleryPhotoUri(): Uri {
        val context = requireContext()
        val imageFolder = File(context.getExternalFilesDir(null), "Pictures/TestCaptures")
        if (!imageFolder.exists()) {
            imageFolder.mkdirs()
        }

        val file = File(imageFolder, "IMG_${System.currentTimeMillis()}.jpg")

        // Menghubungkan ke berkas FileProvider yang telah kamu daftarkan di AndroidManifest sebelumnya
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}