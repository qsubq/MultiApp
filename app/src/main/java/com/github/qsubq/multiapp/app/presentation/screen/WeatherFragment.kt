package com.github.qsubq.multiapp.app.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.qsubq.multiapp.databinding.FragmentWeatherBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.weatherLiveData.observe(viewLifecycleOwner){
            binding.locationTv.text = "Saransk"

            binding.temp.text = it.main.temp.toString()
            binding.tempMax.text = it.main.temp_max.toString()
            binding.tempMin.text = it.main.temp_min.toString()
            binding.humidity.text = it.main.humidity.toString()
            binding.sunrise.text = it.sys.sunrise.toString()
            binding.sunset.text = it.sys.sunset.toString()
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){error ->
            view.let {
                Snackbar.make(it, error, 5000).show()
            }
        }

        viewModel.getWeatherInfo()

    }
}