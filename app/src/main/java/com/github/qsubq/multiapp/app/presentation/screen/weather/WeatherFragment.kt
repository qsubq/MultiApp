package com.github.qsubq.multiapp.app.presentation.screen.weather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.github.qsubq.multiapp.R
import com.github.qsubq.multiapp.data.repository.WeatherRepositoryImpl
import com.github.qsubq.multiapp.data.repository.WeatherRepositoryImpl.*
import com.github.qsubq.multiapp.databinding.FragmentWeatherBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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

        binding.progressBar.visibility = View.INVISIBLE
        binding.detailsContainer.visibility = View.INVISIBLE

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weatherFlow.collect {
                    when (it) {
                        is NetworkResult.Success -> {
                            binding.progressBar.visibility = View.INVISIBLE

                            binding.location.text = it.data.name
                            binding.updatedAt.text =
                                "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(Date(
                                    it.data.dt.toLong() * 1000))

                            binding.status.text = it.data.weather[0].description
                            binding.temp.text = it.data.main.temp.toString() + "°C"
                            binding.tempMax.text = "Max Temp: " + it.data.main.temp_max.toString() + "°C"
                            binding.tempMin.text = "Min Temp: " + it.data.main.temp_min.toString() + "°C"

                            binding.wind.text = it.data.wind.speed.toString()
                            binding.humidity.text = it.data.main.humidity.toString()
                            binding.pressure.text = it.data.main.pressure.toString()
                            binding.sunrise.text = SimpleDateFormat("hh:mm a",
                                Locale.ENGLISH).format(Date(it.data.sys.sunrise.toLong() * 1000))
                            binding.sunset.text = SimpleDateFormat("hh:mm a",
                                Locale.ENGLISH).format(Date(it.data.sys.sunset.toLong() * 1000))

                            binding.detailsContainer.visibility = View.VISIBLE
                        }
                        is NetworkResult.Error -> {
                            showError(it.message.toString())
                        }
                        is NetworkResult.Exception -> {
                            showError(it.e.toString())
                        }
                        is NetworkResult.Loading -> {}
                    }
                }
            }
        }



        binding.searchView.setQuery(getString(R.string.main_city), true)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.progressBar.visibility = View.VISIBLE
                    viewModel.getWeatherInfo(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

    }

    private fun showError(e: String) {
        view.let {
            if (it != null) {
                Snackbar.make(it, e, Snackbar.LENGTH_LONG).show()
            }
        }
        Log.e(tag, e)
        binding.progressBar.visibility = View.INVISIBLE
    }

}