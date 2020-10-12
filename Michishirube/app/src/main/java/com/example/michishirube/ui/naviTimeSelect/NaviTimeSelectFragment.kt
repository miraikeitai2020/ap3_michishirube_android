package com.example.michishirube.ui.naviTimeSelect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentNaviTimeSelectBinding
import com.example.michishirube.ui.NavigationSharedViewModel


class NaviTimeSelectFragment : Fragment() {
    private val sharedViewModel: NavigationSharedViewModel by activityViewModels()
    private lateinit var binding:FragmentNaviTimeSelectBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNaviTimeSelectBinding.inflate(inflater, container, false)
        sharedViewModel.getDeviceLocation(requireActivity(), requireContext())

        //Viewの設定
        binding.timePicker.setIs24HourView(true)
        binding.timePicker.setHour(sharedViewModel.hour)
        binding.timePicker.setMinute(sharedViewModel.minute)

        //return view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TimePickerの値変更したら読み取る
        binding.timePicker.setOnTimeChangedListener { timePicker,hour, minute ->
            sharedViewModel.setTime(hour, minute)
        }

        //決定ボタンを押下
        binding.brTimeDecision.setOnClickListener {
            //サーバとのやりとりが多分ここに入る
            findNavController().navigate(R.id.action_naviTimeSelect_to_naviDestination)
        }
    }
}