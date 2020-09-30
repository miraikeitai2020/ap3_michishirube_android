package com.example.michishirube.ui.naviTimeSelect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentNaviTimeSelectBinding
import kotlinx.android.synthetic.main.fragment_navi_emotion_select.view.*
import kotlinx.android.synthetic.main.fragment_navi_time_select.view.*


class NaviTimeSelectFragment : Fragment() {
    private val naviTimeSelectViewModel: NaviTimeSelectViewModel by viewModels()
    private lateinit var binding:FragmentNaviTimeSelectBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //val view = inflater.inflate(R.layout.fragment_navi_time_select, container, false)
        binding = FragmentNaviTimeSelectBinding.inflate(inflater, container, false)

        //Viewの設定
        binding.timePicker.setIs24HourView(true)
        binding.timePicker.setHour(naviTimeSelectViewModel.hour)
        binding.timePicker.setMinute(naviTimeSelectViewModel.minute)


        //return view
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //画面遷移　決定ボタンタップしたら．　この時の時間のあれってViewModel関与しなくていいの？
        binding.btTimeDecision.setOnClickListener {
            //TimePickerの値変えるやつここに書いて，その中でその変数をViewModelの関数に渡して所要時間を計算し，画面遷移の値に代入かなぁ〜
            findNavController().navigate(R.id.action_naviTimeSelect_to_naviDestination)
        }
    }

}