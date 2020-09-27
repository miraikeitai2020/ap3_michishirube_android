package com.example.michishirube.ui.naviTimeSelect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import kotlinx.android.synthetic.main.fragment_navi_emotion_select.view.*
import kotlinx.android.synthetic.main.fragment_navi_time_select.view.*


class NaviTimeSelectFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_navi_time_select, container, false)

        //Viewの設定
        val timePicker = view.findViewById<TimePicker>(R.id.timePicker)
        timePicker.setIs24HourView(true)
        timePicker.setHour(0)
        timePicker.setMinute(30)

        //画面遷移　決定ボタンタップしたら
        view.btTimeDecision.setOnClickListener{
            findNavController().navigate(R.id.action_naviTimeSelect_to_naviDestination)
        }
        return view
    }

}