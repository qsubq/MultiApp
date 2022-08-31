package com.github.qsubq.multiapp.app.presentation.screen.player

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.github.qsubq.multiapp.R
import com.github.qsubq.multiapp.app.presentation.adapter.PlayerAdapter
import com.github.qsubq.multiapp.databinding.FragmentPlayerBinding

class PlayerFragment : Fragment() {
    private lateinit var binding: FragmentPlayerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPlayerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PlayerAdapter()
        binding.rcViewPlayer.adapter = adapter
        binding.rcViewPlayer.layoutManager = GridLayoutManager(this.context,2)
    }
}