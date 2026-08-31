package com.example.alarmapplication

import android.Manifest
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.alarmapplication.databinding.ActivityMainBinding
import com.example.alarmapplication.ui.theme.AlarmReceiver
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var picker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (!isGranted) {
            Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()
        requestNotificationPermission()

        binding.selectedAlarmBtn.setOnClickListener{
            showTimePicker()
        }

        binding.setAalrmBtn.setOnClickListener{
          setAlarm()
        }

        binding.cancleAlarmBtn.setOnClickListener{
            cancelAlarm()
        }

    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun cancelAlarm(){
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
        Toast.makeText(this,"Alarm cancelled", Toast.LENGTH_LONG).show()



    }


    private fun setAlarm() {
        if (!::calendar.isInitialized) {
            Toast.makeText(this, "Please select a time first", Toast.LENGTH_SHORT).show()
            return
        }
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,pendingIntent,
        )
        Toast.makeText(this,"Alarm set successfully", Toast.LENGTH_LONG).show()


    }
    private fun showTimePicker(){
          picker = MaterialTimePicker.Builder()
              .setTimeFormat(TimeFormat.CLOCK_12H)
              .setHour(12)
              .setMinute(0)
              .setTitleText("Select Aalrm Time")
              .build()

        picker.show(supportFragmentManager,"forandroid")

        picker.addOnPositiveButtonClickListener {
            val hour = picker.hour
            val minute = picker.minute
            val amPm = if (hour < 12) "AM" else "PM"
            val displayHour = when {
                hour == 0 -> 12
                hour > 12 -> hour - 12
                else -> hour
            }
            binding.selectedTime.text = String.format(Locale.getDefault(), "%02d:%02d %s", displayHour, minute, amPm)

            calendar = Calendar.getInstance()
            calendar [Calendar.HOUR_OF_DAY] = picker.hour
            calendar [Calendar.MINUTE] = picker.minute
            calendar [Calendar.SECOND] = 0
            calendar [Calendar.MILLISECOND] = 0
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val name : CharSequence = "foxandroidRemainderChannel"
        val description = "Channel For Alarm Manager"
         val importance = NotificationManager.IMPORTANCE_HIGH
          val channel = NotificationChannel("forandroid", name,importance)
           channel.description = description
           val notifictionManager = getSystemService(
               NotificationManager::class.java
           )
            notifictionManager.createNotificationChannel(channel)
     }
    }
}