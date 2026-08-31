# Alarm App – Android Kotlin

## 📱 Project Description

The **Alarm App** is an Android application developed using **Kotlin** and **Android Studio**. The app allows users to create, schedule, manage, and cancel alarms at specific times. When an alarm time is reached, the application triggers a notification and alarm alert to remind the user.

The application uses Android's **AlarmManager** and **PendingIntent** to schedule alarms, while **BroadcastReceiver** is used to receive the alarm event and trigger the notification.

## 🚀 Main Features

* ⏰ Set alarms for a specific time
* 🔔 Display alarm notifications
* 🔄 Enable or disable alarms
* ❌ Cancel scheduled alarms
* 📅 Select hour and minute using a TimePicker
* 📱 Works with Android notification channels
* 🔐 Handles notification permissions on newer Android versions
* ⚡ Uses Android AlarmManager for accurate scheduling
* 🎨 Simple and user-friendly interface
* 🔄 Supports repeating or scheduled alarm functionality

## 🛠️ Technologies Used

| Technology              | Purpose                              |
| ----------------------- | ------------------------------------ |
| **Kotlin**              | Main programming language            |
| **Android Studio**      | Development environment              |
| **AlarmManager**        | Scheduling alarms                    |
| **PendingIntent**       | Delivering alarm events              |
| **BroadcastReceiver**   | Receiving scheduled alarm events     |
| **NotificationManager** | Displaying notifications             |
| **NotificationChannel** | Managing notifications on Android 8+ |
| **View Binding**        | Accessing XML UI components          |
| **TimePicker**          | Selecting alarm time                 |

## ⚙️ How It Works

1. The user opens the Alarm App.
2. The user selects a time using the **TimePicker**.
3. The application creates a `PendingIntent`.
4. `AlarmManager` schedules the alarm.
5. At the selected time, Android sends the alarm event to the `BroadcastReceiver`.
6. The receiver creates and displays a notification.
7. The user receives an alarm reminder.

## 📂 Project Structure

```text
AlarmApplication/
│
├── MainActivity.kt
├── AlarmReceiver.kt
│
├── res/
│   ├── layout/
│   │   └── activity_main.xml
│   │
│   ├── drawable/
│   │
│   ├── mipmap/
│   │
│   └── values/
│       ├── strings.xml
│       ├── colors.xml
│       └── themes.xml
│
└── AndroidManifest.xml
```

## 🎯 Project Objective

The main objective of this project is to develop a practical Android alarm application while learning how to work with **Kotlin, AlarmManager, PendingIntent, BroadcastReceiver, and Android Notifications**.

This project also demonstrates how Android applications can perform scheduled tasks even when the application is not actively open.

## 📌 Future Improvements

* Multiple alarms
* Repeat alarms for selected days
* Custom alarm sounds
* Snooze functionality
* Delete/edit alarm functionality
* Alarm history
* Dark mode
* Custom notification actions
* Persistent alarm storage using Room Database
* Full-screen alarm interface

## 👨‍💻 Development

**Platform:** Android
**Language:** Kotlin
**IDE:** Android Studio
**UI:** XML / View Binding
**Application Type:** Alarm & Reminder App
