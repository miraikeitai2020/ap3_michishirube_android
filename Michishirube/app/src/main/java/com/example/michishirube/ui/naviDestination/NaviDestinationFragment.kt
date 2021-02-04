package com.example.michishirube.ui.naviDestination

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.michishirube.NotificationService
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentNaviDestinationBinding
import com.example.michishirube.ui.NavigationSharedViewModel


class NaviDestinationFragment : Fragment() {
    private lateinit var binding: FragmentNaviDestinationBinding
    private val sharedViewModel: NavigationSharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNaviDestinationBinding.inflate(inflater, container, false)

        val destinationNameObserver = Observer<String>{newName ->
            binding.tvDesc.text = newName
        }

        sharedViewModel.spotName.observe(this,destinationNameObserver)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkBackgroundPermission()
        val dataStore: SharedPreferences? = activity?.getPreferences(Context.MODE_PRIVATE)

        binding.ibGo.setOnClickListener{
            startActivity(sharedViewModel.intentDetour())
            findNavController().navigate(R.id.action_naviDestination_to_naviNavigating)
            val serviceIntent = Intent(requireActivity(), NotificationService::class.java)
            serviceIntent.putExtra("route",0)
            activity?.startForegroundService(serviceIntent)

            with(dataStore?.edit()) {
                this?.putFloat("spotLatitude",sharedViewModel.spotLatitude?.toFloat() ?: 35.70013272104651.toFloat())
                this?.putFloat("spotLongitude",sharedViewModel.spotLongitude?.toFloat() ?: 139.5760456919909.toFloat())
                this?.apply()
            }
        }


    }

    private fun checkBackgroundPermission(){
        val permissionAccessCoarseLocationApproved = ActivityCompat
        .checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED

        if (permissionAccessCoarseLocationApproved) {

        } else {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),1000
            )
        }

    }


}