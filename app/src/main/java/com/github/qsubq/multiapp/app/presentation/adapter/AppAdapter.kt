package com.github.qsubq.multiapp.app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.github.qsubq.multiapp.R
import com.github.qsubq.multiapp.databinding.MainItemLayoutBinding

class AppAdapter : RecyclerView.Adapter<AppAdapter.AppViewHolder>() {
    class AppViewHolder(val binding: MainItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val itemBinding =
            MainItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val context = holder.itemView.context as AppCompatActivity
        when (position) {
            0 -> {
                holder.binding.itemTv.text = context.getString(R.string.weather)
                holder.binding.itemImage.setImageResource(R.mipmap.ic_weather_icon_round)
            }
            1->{
                holder.binding.itemTv.text = context.getString(R.string.player)
            }
        }

        holder.itemView.setOnClickListener { v ->

            val navHostFragment =
                context.supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            when (position) {
                0 -> navController.navigate(R.id.action_startFragment_to_weatherFragment)
                1 -> navController.navigate(R.id.action_startFragment_to_playerFragment)
            }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

}


