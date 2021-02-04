package com.example.michishirube.ui.naviTimeSelect

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentNaviTimeSelectBinding
import com.example.michishirube.ui.NavigationSharedViewModel
import kotlinx.android.synthetic.main.fragment_navi_time_select.*


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

        val minutePicker = binding.timePicker.findViewById<NumberPicker>(Resources.getSystem().getIdentifier("minute","id","android"))
        minutePicker.setMinValue(0)
        minutePicker.setMaxValue(5)
        val displayValues = arrayOf("0", "10", "20", "30", "40", "50")
        minutePicker.setDisplayedValues(displayValues)

        //return view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.resetSpotName()
        //TimePickerの値変更したら読み取る
        binding.timePicker.setOnTimeChangedListener { timePicker, hour, minute ->
            sharedViewModel.setTime(hour, minute)
        }

        //決定ボタンを押下
        binding.ibTimeDecision.setOnClickListener {
            //サーバとのやりとりが多分ここに入る
            sharedViewModel.loadDestination(requireContext())
            findNavController().navigate(R.id.action_naviTimeSelect_to_naviDestination)
        }
    }
}