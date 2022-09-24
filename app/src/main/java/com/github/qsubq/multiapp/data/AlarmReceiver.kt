package com.github.qsubq.multiapp.data

import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.github.qsubq.multiapp.R
import com.github.qsubq.multiapp.app.presentation.MainActivity

class AlarmReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(p0: Context?, p1: Intent?) {

        val intent = Intent(p0, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(p0, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = Notification.Builder(p0, "foxandroid")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Content Title")
            .setContentText("Content Text")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
        p0?.let { NotificationManagerCompat.from(it) }?.notify(123, builder.build())

    }
}