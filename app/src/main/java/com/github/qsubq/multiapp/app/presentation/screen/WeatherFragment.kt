package com.github.qsubq.multiapp.app.presentation.screen

import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.VibratorManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.qsubq.multiapp.databinding.FragmentWeatherBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.weatherLiveData.observe(viewLifecycleOwner) {
            binding.locationTv.text = it.name
            binding.updatedAtTv.text =
                "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(
                    it.dt.toLong() * 1000))

            binding.status.text = it.weather[0].description

            binding.temp.text = it.main.temp.toString() + "°C"
            binding.tempMax.text = "Max Temp: " + it.main.temp_max.toString() + "°C"
            binding.tempMin.text = "Min Temp: " + it.main.temp_min.toString() + "°C"

            binding.wind.text = it.wind.speed.toString()
            binding.humidity.text = it.main.humidity.toString()
            binding.pressure.text = it.main.pressure.toString()
            binding.sunrise.text = SimpleDateFormat("hh:mm a",
                Locale.ENGLISH).format(Date(it.sys.sunrise.toLong() * 1000))
            binding.sunset.text = SimpleDateFormat("hh:mm a",
                Locale.ENGLISH).format(Date(it.sys.sunset.toLong() * 1000))

        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
            view.let {
                Snackbar.make(it, error, 8000)
                    .setAction("Try again") {
                        viewModel.getWeatherInfo()
                    }
                    .show()
            }
        }

        viewModel.getWeatherInfo()
    }
}