package com.example.michishirube.ui.naviTimeSelect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentNaviTimeSelectBinding
import com.example.michishirube.ui.NavigationSharedViewModel
import kotlinx.android.synthetic.main.fragment_navi_emotion_select.view.*
import kotlinx.android.synthetic.main.fragment_navi_time_select.view.*


class NaviTimeSelectFragment : Fragment() {
    private val viewModel: NavigationSharedViewModel by viewModels()
    private lateinit var binding:FragmentNaviTimeSelectBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNaviTimeSelectBinding.inflate(inflater, container, false)

        //Viewの設定
        binding.timePicker.setIs24HourView(true)
        binding.timePicker.setHour(viewModel.hour)
        binding.timePicker.setMinute(viewModel.minute)

        //return view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TimePickerの値変更したら読み取る
        binding.timePicker.setOnTimeChangedListener { timePicker,hour, minute ->
            viewModel.onTimeSet(hour, minute)
        }

        //決定ボタンを押下
        binding.btTimeDecision.setOnClickListener {
            findNavController().navigate(R.id.action_naviTimeSelect_to_naviDestination)
        }
    }

}