package com.example.alarmapplication.ui.theme

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.alarmapplication.R

class AlarmReceiver : BroadcastReceiver(){
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        val i = Intent(context, DestinationActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(context, "forandroid")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Foxandroid Alarm Manager")
            .setContentText("Subscribe for more android related content")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(123, builder.build())
        }
    }
}