package com.example.suciapps.pertemuan_5

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.suciapps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ TOOLBAR
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web Merdeka"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // ==============================
        // ✅ LOADING BAR
        // ==============================
        binding.progressBar.visibility = View.VISIBLE

        // ✅ WEBVIEW
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: android.webkit.WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
            }
        }

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://merdeka.com")

        // ==============================
        // ✅ SCROLL BEHAVIOR (FIX)
        // ==============================
        binding.webView.setOnTouchListener { view, event ->
            binding.appBar.setExpanded(false, true)
            false
        }

        // ==============================
        // ✅ BACK BUTTON
        // ==============================
        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (binding.webView.canGoBack()) {
                        binding.webView.goBack()
                    } else {
                        finish()
                    }
                }
            }
        )
    }

    // ✅ BACK TOOLBAR
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}