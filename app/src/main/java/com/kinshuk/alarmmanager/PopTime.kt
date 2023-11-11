package com.kinshuk.alarmmanager

import androidx.fragment.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker

class PopTime: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView = inflater.inflate(R.layout.pop_time,container,false)

        var budone = myView.findViewById(R.id.DoneBtn) as Button
        var tp = myView.findViewById(R.id.picker) as TimePicker
        budone.setOnClickListener{
            val ma = activity as MainActivity
            ma.SetTime(tp.hour,tp.minute)
            this.dismiss()
        }

        return myView
    }
}