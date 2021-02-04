package com.example.michishirube.ui.naviDetourEvaluation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.michishirube.NotificationService
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentNaviDestinationBinding
import com.example.michishirube.databinding.FragmentNaviDetourEvaluationBinding
import com.example.michishirube.ui.NavigationSharedViewModel
import kotlinx.android.synthetic.main.fragment_navi_detour_evaluation.view.*


class naviDetourEvaluationFragment : Fragment() {
    private lateinit var binding: FragmentNaviDetourEvaluationBinding
    private val sharedViewModel: NavigationSharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNaviDetourEvaluationBinding.inflate(inflater, container, false)
        val dataStore: SharedPreferences? = activity?.getPreferences(Context.MODE_PRIVATE)
        val spotLatitude = dataStore?.getFloat("spotLatitude",35.70013272104651.toFloat())
        val spotLongitude = dataStore?.getFloat("spotLongitude",139.5760456919909.toFloat())

        sharedViewModel.setDestination(spotLatitude?.toDouble(), spotLongitude?.toDouble())


        val serviceIntent = Intent(requireActivity(), NotificationService::class.java)
        serviceIntent.putExtra("route",1)

        binding.btGood.setOnClickListener {
            startActivity(sharedViewModel.intentDestination())
            findNavController().navigate(R.id.action_naviDetourEvaluation_to_naviNavigating)

            activity?.startForegroundService(serviceIntent)
        }
        binding.btBad.setOnClickListener {
            startActivity(sharedViewModel.intentDestination())
            findNavController().navigate(R.id.action_naviDetourEvaluation_to_naviNavigating)
            activity?.startForegroundService(serviceIntent)
        }
        return binding.root
    }
}