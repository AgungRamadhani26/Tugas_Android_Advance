package com.example.tugasandroidadvance.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tugasandroidadvance.databinding.FragmentAboutDeveloperBinding

class aboutDeveloperFragment : Fragment() {
    //Binding fragment about developer
    private lateinit var binding: FragmentAboutDeveloperBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout untuk fragment home
        binding = FragmentAboutDeveloperBinding.inflate(inflater, container, false)
        val view = binding.root

        val linkedin = binding.ivLinkedinDeveloper
        linkedin.setOnClickListener{
            val linkedinProfilUrl = "https://www.linkedin.com/in/agung-ramadhani-915a89244/"

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkedinProfilUrl))
            startActivity(intent)
        }
        return view
    }
}