package com.example.tugasandroidadvance.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieAnimationView
import com.example.tugasandroidadvance.R
import com.example.tugasandroidadvance.databinding.FragmentAboutAppBinding

class aboutAppFragment : Fragment() {
    //Binding fragment about app
    private lateinit var binding: FragmentAboutAppBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout untuk fragment home
        binding = FragmentAboutAppBinding.inflate(inflater, container, false)
        val view = binding.root

        val animationView: LottieAnimationView = view.findViewById(R.id.lottieAnimationView)
        animationView.speed = 2.0f

        return view
    }
}