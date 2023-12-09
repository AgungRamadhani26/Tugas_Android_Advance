package com.example.tugasandroidadvance.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tugasandroidadvance.R
import com.example.tugasandroidadvance.databinding.ActivityBottomNavbarBinding
import com.example.tugasandroidadvance.fragment.HomeFragment
import com.example.tugasandroidadvance.fragment.aboutAppFragment
import com.example.tugasandroidadvance.fragment.aboutDeveloperFragment
import com.example.tugasandroidadvance.viewmodels.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavbarActivity : AppCompatActivity() {
    //binding activity_bottom_navbar.xml
    private lateinit var binding: ActivityBottomNavbarBinding
    private lateinit var bottomNavbarViewModel: ViewModel
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavbarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(aboutAppFragment())

        bottomNavbarViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        binding.bottomNav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.home -> bottomNavbarViewModel.setFragment(HomeFragment())
                R.id.about_app -> bottomNavbarViewModel.setFragment(aboutAppFragment())
                R.id.about_developer-> bottomNavbarViewModel.setFragment(aboutDeveloperFragment())
            }
            return@setOnItemSelectedListener true
        }

        bottomNavbarViewModel.selectedFragment.observe(this, Observer {
            setFragment(it)
        })

        // Mengambil informasi dari intent
        val fragmentToShow = intent.getStringExtra("FRAGMENT_TO_SHOW")
        if (fragmentToShow == "HOME_FRAGMENT") {
            bottomNavbarViewModel.setFragment(HomeFragment())

            // Atur item navigasi "Home" sebagai yang aktif
            bottomNavigationView = findViewById(R.id.bottomNav)
            bottomNavigationView .selectedItemId = R.id.home
        }
    }


    private fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        // menukar fragment
        transaction.replace(R.id.fragmentFl, fragment)
        // untuk tidak menumpuk fragment
        transaction.disallowAddToBackStack()
        // menyatakan transaction di jalankan
        transaction.commit()
    }
}