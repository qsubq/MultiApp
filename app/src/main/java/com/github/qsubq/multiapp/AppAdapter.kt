package com.github.qsubq.multiapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
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
        when (position) {
            0 -> {
                holder.binding.itemTv.text = "Weather"
                holder.binding.itemImage.setImageResource(R.mipmap.ic_weather_icon_round)
            }
        }

        holder.itemView.setOnClickListener { v ->
            val activity = v!!.context as AppCompatActivity
            val navHostFragment =
                activity.supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            if (position == 0) {
                navController.navigate(R.id.action_startFragment_to_weatherFragment)
            }
        }

    }

    override fun getItemCount(): Int {
        return 1
    }

}


