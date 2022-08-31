package com.github.qsubq.multiapp.app.presentation.adapter

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.multiapp.R
import com.github.qsubq.multiapp.databinding.PlayerItemLayoutBinding
import kotlin.math.ln

class PlayerAdapter : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    class PlayerViewHolder(val binding: PlayerItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding =
            PlayerItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        when (position) {
            0 -> holder.binding.imgPlayer.setImageResource(R.drawable.rain)
        }
        val activity = holder.itemView.context as AppCompatActivity
        val rainPlayer: MediaPlayer = MediaPlayer.create(activity, R.raw.rain)
        holder.binding.imgPlayer.setOnClickListener {
            when (position) {
                0 -> {
                    if (rainPlayer.isPlaying) {
                        rainPlayer.pause()
                        rainPlayer.seekTo(0)
                        rainPlayer.isLooping = false
                    } else {
                        rainPlayer.start()
                        rainPlayer.isLooping = true
                    }
                }
            }
        }
        holder.binding.seekbarSound.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                val maxVolume = 100
                val volume = (1 - (ln((maxVolume - p1).toDouble()) / ln(
                    maxVolume.toDouble()))).toFloat()
                rainPlayer.setVolume(volume, volume)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    override fun getItemCount(): Int {
        return 1
    }

}