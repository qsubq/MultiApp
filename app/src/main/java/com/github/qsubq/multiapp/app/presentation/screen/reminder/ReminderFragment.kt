package com.github.qsubq.multiapp.app.presentation.screen.reminder

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.github.qsubq.multiapp.data.AlarmReceiver
import com.github.qsubq.multiapp.databinding.FragmentReminderBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class ReminderFragment : Fragment() {
    private lateinit var binding: FragmentReminderBinding
    private lateinit var picker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentReminderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createNotificationChanel()

        binding.btnSelectTime.setOnClickListener {
            showTimePicker()
        }
        binding.btnSet.setOnClickListener {
            setAlarm()
        }
        binding.btnCancel.setOnClickListener {
            cancelAlarm()
        }
    }

    private fun cancelAlarm() {
        alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(activity, AlarmReceiver::class.java)
        pendingIntent =
            PendingIntent.getBroadcast(activity, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.cancel(pendingIntent)

        Toast.makeText(activity, "Alarm cancel successfully", Toast.LENGTH_SHORT).show()
    }

    private fun setAlarm() {
        alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(activity, AlarmReceiver::class.java)
        pendingIntent =
            PendingIntent.getBroadcast(activity, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        Toast.makeText(activity, "Alarm set successfully", Toast.LENGTH_LONG).show()
    }

    private fun showTimePicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Select Alarm Time")
            .build()
        activity?.let { picker.show(it.supportFragmentManager, "foxandroid") }

        picker.addOnPositiveButtonClickListener {
            if (picker.hour > 12) {
                binding.tvTime.text =
                    String.format("%02d", picker.hour - 12) + " : " + String.format("%02d",
                        picker.minute) + " PM"
            } else {
                binding.tvTime.text =
                    picker.hour.toString() + " : " + picker.minute.toString() + " AM"
            }
            calendar = Calendar.getInstance()
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "foxandroidReminderChannel"
            val desc = "Desc"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel("foxandroid", name, importance)
            notificationChannel.description = desc
            activity?.let { getSystemService(it, NotificationManager::class.java) }
                ?.createNotificationChannel(notificationChannel)
        }
    }
}