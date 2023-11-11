package com.kinshuk.alarmmanager

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kinshuk.alarmmanager.databinding.ActivityMainBinding
import kotlin.math.min


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val saveData = SaveData(applicationContext)
        setTV(saveData.getHour(),saveData.getMinute())
    }

    fun BuSetTime(view: View) {
        val poptym = PopTime()
        val fm = supportFragmentManager
        poptym.show(fm,"Select Time")
    }

    fun SetTime(hours:Int,minutes:Int){
            setTV(hours,minutes)
            val saveData = SaveData(applicationContext)
            saveData.saveData(hours,minutes)
            saveData.setAlarm()

    }
    fun setTV(hours: Int,minutes: Int)
    {
        var am_pm:String
        var hr = hours
        var final_hrs:String
        var final_min:String
        if(hr.equals(0))
        {
            am_pm = "AM"
            hr = 12
        }
        else if(hours<12)
            am_pm = "AM"
        else
        {
            am_pm = "PM"
            hr -= 12
        }
        if(hr<10)
            final_hrs = "0$hr"
        else
            final_hrs = hr.toString()
        if(minutes<10)
            final_min = "0$minutes"
        else
            final_min = minutes.toString()
        val time = "$final_hrs:$final_min $am_pm"
        binding.ShowTime.text = time
    }
}