package com.kinshuk.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import java.util.Calendar

class SaveData {

    var context: Context?=null
    var sharedRef: SharedPreferences?=null
    constructor(context: Context){
        this.context=context
        this.sharedRef = context.getSharedPreferences("myref",Context.MODE_PRIVATE)
    }
    fun saveData(hour:Int , min:Int)
    {
        var editor = sharedRef!!.edit()
        editor.putInt("hour",hour)
        editor.putInt("minute",min)
        editor.commit()
    }
    fun getHour():Int{
        return sharedRef!!.getInt("hour",0)
    }
    fun getMinute():Int{
        return sharedRef!!.getInt("minute",0)
    }

    fun setAlarm(){
        var hour:Int = getHour()
        var min:Int = getMinute()
        val calender = Calendar.getInstance()
        calender.set(Calendar.HOUR_OF_DAY,hour)
        calender.set(Calendar.MINUTE,min)
        calender.set(Calendar.SECOND,0)

        val am =context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager



        val intent  = Intent(context,myBroadcastReceiver::class.java)
        intent.putExtra("message","alarm time")
        intent.putExtra("hour",getHour())
        intent.putExtra("min",getMinute())
        intent.action = "com.tester.alarmmanager"
        val pi = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_MUTABLE)
        am.setExact(AlarmManager.RTC, calender.timeInMillis, pi)

    }
}