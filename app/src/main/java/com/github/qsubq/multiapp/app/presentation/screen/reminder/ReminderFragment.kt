package com.github.qsubq.multiapp.app.presentation.screen.reminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.qsubq.multiapp.R
import com.github.qsubq.multiapp.databinding.FragmentReminderBinding

class ReminderFragment : Fragment() {
    private lateinit var binding: FragmentReminderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentReminderBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}