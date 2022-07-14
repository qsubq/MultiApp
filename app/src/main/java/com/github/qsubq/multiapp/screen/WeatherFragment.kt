package com.github.qsubq.multiapp.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.qsubq.multiapp.R
import com.github.qsubq.multiapp.databinding.FragmentWeatherBinding


class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

}