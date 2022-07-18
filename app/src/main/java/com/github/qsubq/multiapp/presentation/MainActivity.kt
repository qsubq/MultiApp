package com.github.qsubq.multiapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.qsubq.multiapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}