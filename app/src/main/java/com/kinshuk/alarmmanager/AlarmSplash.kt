package com.kinshuk.alarmmanager

import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kinshuk.alarmmanager.databinding.ActivityAlarmSplashBinding
import com.kinshuk.alarmmanager.databinding.ActivityMainBinding

class AlarmSplash : AppCompatActivity() {
    private var ringtone: Ringtone? = null
    private lateinit var binding: ActivityAlarmSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmSplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent?.extras

        if (bundle != null) {
            val hr = bundle.getInt("hour", 0)
            val min = bundle.getInt("min", 0)
            setTV(hr, min)
            startAlarm()
        }

    }

    private fun startAlarm() {
        val alarmSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(this, alarmSound)
        ringtone!!.play()
    }

    fun dismiss(view: View) {
        ringtone?.stop()
        finish()
    }

    fun snooze(view: View) {
        val saveData = SaveData(applicationContext)
        var hr = saveData.getHour()
        var mi = saveData.getMinute()
        mi += 5;
        if(mi>=60)
        {
            mi = mi%60
            hr += 1
            if(hr == 24)
                hr =0
        }
        saveData.saveData(hr,mi)
        saveData.setAlarm()
        dismiss(view)
    }

    private fun setTV(hours: Int, minutes: Int) {
        var am_pm: String
        var hr = hours
        var final_hrs: String
        var final_min: String

        if (hr == 0) {
            am_pm = "AM"
            hr = 12
        } else if (hours < 12) {
            am_pm = "AM"
        } else {
            am_pm = "PM"
            hr -= 12
        }

        final_hrs = if (hr < 10) "0$hr" else hr.toString()
        final_min = if (minutes < 10) "0$minutes" else minutes.toString()

        val time = "$final_hrs:$final_min $am_pm !!"
        binding.alarmTv.text = time
    }
}
