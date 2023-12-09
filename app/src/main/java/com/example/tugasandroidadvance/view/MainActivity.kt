package com.example.tugasandroidadvance.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.tugasandroidadvance.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Menyembunyikan status bar dan membuat splash screen sebagai full screen activity
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //Menggunakan postDelayed(Runnable, time) untuk mengirim delay waktu
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, BottomNavbarActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) // delay waktu 2 detik
    }
}