package com.example.suciapps.Home.pertemuan_13

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.suciapps.databinding.FragmentTabScanBinding
import com.example.suciapps.utils.PermissionHelper
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TabScanFragment : Fragment() {

    private var _binding: FragmentTabScanBinding? = null
    private val binding get() = _binding!!

    // Inisialisasi variabel sesuai langkah 4 modul
    private lateinit var cameraExecutor: ExecutorService

    // Khusus hanya format QR Code
    private var scanner = BarcodeScanning.getClient(
        BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
            .build()
    )

    // Launcher untuk izin modern
    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            startCamera()
        } else {
            Toast.makeText(context, "Izin kamera diperlukan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Function onViewCreated Diperbarui Sesuai Modul (Menggunakan PermissionHelper)
    @ExperimentalGetImage
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cameraExecutor = Executors.newSingleThreadExecutor()

        // Menyelaraskan logika dengan pemanggilan hasPermission & requestPermission
        if (!PermissionHelper.hasPermission(requireActivity(), Manifest.permission.CAMERA)) {
            PermissionHelper.requestPermission(
                permissionLauncher,
                Manifest.permission.CAMERA
            )
        } else {
            startCamera()
        }
    }

    // 🛠️ SUDAH DISESUAIKAN DENGAN ID XML KAMU (previewView & tvScanResult)
    @ExperimentalGetImage
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            // 1. Atur Tampilan Preview Kamera
            val preview = Preview.Builder().build().also {
                // 🛠️ DISESUAIKAN: Menggunakan previewView sesuai dengan ID di XML kamu
                it.setSurfaceProvider(binding.previewView.surfaceProvider)
            }

            // 2. Atur Analisis Gambar untuk Pemindaian ML Kit
            val imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            imageAnalysis.setAnalyzer(cameraExecutor) { imageProxy ->
                val mediaImage = imageProxy.image
                if (mediaImage != null) {
                    val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

                    scanner.process(image)
                        .addOnSuccessListener { barcodes ->
                            for (barcode in barcodes) {
                                val rawValue = barcode.rawValue
                                if (!rawValue.isNullOrEmpty()) {
                                    // 🛠️ DISESUAIKAN: Menampilkan hasil teks scan langsung ke TextView tvScanResult di layar
                                    binding.tvScanResult.text = "Hasil: $rawValue"
                                }
                            }
                        }
                        .addOnFailureListener {
                            // Menangani kegagalan deteksi jika diperlukan
                        }
                        .addOnCompleteListener {
                            imageProxy.close()
                        }
                } else {
                    imageProxy.close()
                }
            }

            // 3. Memilih Kamera Belakang secara Default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    viewLifecycleOwner,
                    cameraSelector,
                    preview,
                    imageAnalysis
                )
            } catch (exc: Exception) {
                Toast.makeText(context, "Gagal membuka kameraX", Toast.LENGTH_SHORT).show()
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    // Perbaikan pada onDestroyView untuk mencegah memory leak
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        // Perbaikan: scanner diakses langsung tanpa tanda tanya (?) karena tipenya non-nullable
        scanner.close()
        cameraExecutor.shutdown()
    }
}