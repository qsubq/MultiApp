package com.github.qsubq.multiapp.app.presentation.adapter

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
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
            1 -> holder.binding.imgPlayer.setImageResource(R.drawable.lighting)
            2 -> holder.binding.imgPlayer.setImageResource(R.drawable.storm)
            3 -> holder.binding.imgPlayer.setImageResource(R.drawable.bird)
            4 -> holder.binding.imgPlayer.setImageResource(R.drawable.desert_wind)
            5 -> holder.binding.imgPlayer.setImageResource(R.drawable.forest_wind)
            6 -> holder.binding.imgPlayer.setImageResource(R.drawable.grasshopper)
            7 -> holder.binding.imgPlayer.setImageResource(R.drawable.river)
        }
        val activity = holder.itemView.context

        val rainPlayer: MediaPlayer = MediaPlayer.create(activity, R.raw.rain)
        val riverPlayer: MediaPlayer = MediaPlayer.create(activity, R.raw.river)
        val stormPlayer: MediaPlayer = MediaPlayer.create(activity, R.raw.storming_ocean)
        val birdPlayer: MediaPlayer = MediaPlayer.create(activity, R.raw.birds)
        val desertWindPlayer: MediaPlayer = MediaPlayer.create(activity, R.raw.desert_wind)
        val forestWindPlayer: MediaPlayer = MediaPlayer.create(activity, R.raw.forest_wind)
        val grasshopperPlayer: MediaPlayer = MediaPlayer.create(activity, R.raw.grasshopper)
        val lightingPlayer: MediaPlayer = MediaPlayer.create(activity, R.raw.lighting)
        holder.binding.imgPlayer.setOnClickListener {
            when (position) {
                0 -> {
                    if (rainPlayer.isPlaying) {
                        rainPlayer.pause()
                        rainPlayer.seekTo(0)
                    } else {
                        rainPlayer.start()
                        rainPlayer.isLooping = true
                    }
                }
                1 -> {
                    if (lightingPlayer.isPlaying) {
                        lightingPlayer.pause()
                        lightingPlayer.seekTo(0)
                    } else {
                        lightingPlayer.start()
                        lightingPlayer.isLooping = true
                    }
                }
                2 -> {
                    if (stormPlayer.isPlaying) {
                        stormPlayer.pause()
                        stormPlayer.seekTo(0)
                    } else {
                        stormPlayer.start()
                        stormPlayer.isLooping = true
                    }
                }
                3 -> {
                    if (birdPlayer.isPlaying) {
                        birdPlayer.pause()
                        birdPlayer.seekTo(0)
                    } else {
                        birdPlayer.start()
                        birdPlayer.isLooping = true
                    }
                }
                4 -> {
                    if (desertWindPlayer.isPlaying) {
                        desertWindPlayer.pause()
                        desertWindPlayer.seekTo(0)
                    } else {
                        desertWindPlayer.start()
                        desertWindPlayer.isLooping = true
                    }
                }
                5 -> {
                    if (forestWindPlayer.isPlaying) {
                        forestWindPlayer.pause()
                        forestWindPlayer.seekTo(0)
                    } else {
                        forestWindPlayer.start()
                        forestWindPlayer.isLooping = true
                    }
                }
                6 -> {
                    if (grasshopperPlayer.isPlaying) {
                        grasshopperPlayer.pause()
                        grasshopperPlayer.seekTo(0)
                    } else {
                        grasshopperPlayer.start()
                        grasshopperPlayer.isLooping = true
                    }
                }
                7 -> {
                    if (riverPlayer.isPlaying) {
                        riverPlayer.pause()
                        riverPlayer.seekTo(0)
                    } else {
                        riverPlayer.start()
                        riverPlayer.isLooping = true
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
                riverPlayer.setVolume(volume, volume)
                stormPlayer.setVolume(volume, volume)
                birdPlayer.setVolume(volume, volume)
                desertWindPlayer.setVolume(volume, volume)
                forestWindPlayer.setVolume(volume, volume)
                grasshopperPlayer.setVolume(volume, volume)
                lightingPlayer.setVolume(volume, volume)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }

    override fun getItemCount(): Int {
        return 8
    }

}