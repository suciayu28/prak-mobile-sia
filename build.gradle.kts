// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    // 🛠️ TAMBAHAN: Mendaftarkan plugin compiler KSP untuk Room Database
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}