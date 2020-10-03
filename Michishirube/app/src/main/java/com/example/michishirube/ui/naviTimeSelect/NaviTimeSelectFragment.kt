package com.example.michishirube.ui.naviTimeSelect

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentNaviTimeSelectBinding
import com.example.michishirube.ui.NavigationSharedViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kotlin.math.log


class NaviTimeSelectFragment : Fragment() {
    private val sharedViewModel: NavigationSharedViewModel by activityViewModels()
    private val viewModel: NaviTimeSelectViewModel by viewModels()
    private lateinit var binding:FragmentNaviTimeSelectBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNaviTimeSelectBinding.inflate(inflater, container, false)
        viewModel.getDeviceLocation(requireActivity(), requireContext())

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
        binding.btTimeDecision.setOnClickListener {
            sharedViewModel.setLocation(viewModel.lat, viewModel.lon)
            //サーバとのやりとりが多分ここに入る
            findNavController().navigate(R.id.action_naviTimeSelect_to_naviDestination)
        }
    }
}