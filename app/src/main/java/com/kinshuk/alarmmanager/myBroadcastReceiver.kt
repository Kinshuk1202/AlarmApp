package com.kinshuk.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
class myBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action.equals("com.tester.alarmmanager")) {
            var b = intent.extras
//            val toastMessage = b!!.getString("message")
//            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()

            // If you want to start an activity, ensure to use FLAG_ACTIVITY_NEW_TASK
            val startIntent = Intent(context, AlarmSplash::class.java)
            startIntent.putExtra("hour",b!!.getInt("hour"))
            startIntent.putExtra("min", b.getInt("min"))
            startIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(startIntent)
        } else if (intent.action.equals("android.intent.action.BOOT_COMPLETED")) {
            val saveData = SaveData(context!!)
            saveData.setAlarm()
        }
    }
}
