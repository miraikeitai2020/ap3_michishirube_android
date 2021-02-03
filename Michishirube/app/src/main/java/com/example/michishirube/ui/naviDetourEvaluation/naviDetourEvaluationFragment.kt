package com.example.michishirube.ui.naviDetourEvaluation

import android.content.Intent
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
        val view =inflater.inflate(R.layout.fragment_navi_detour_evaluation, container, false)
        binding.btGood.setOnClickListener {
            val uriStr = "https://www.google.com/maps/dir/?api=1&destination=${sharedViewModel.spotLatitude},${sharedViewModel.spotLongitude}"
            val uri = Uri.parse(uriStr)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
            findNavController().navigate(R.id.action_naviDetourEvaluation_to_naviNavigating)
            val serviceIntent = Intent(requireActivity(), NotificationService::class.java)
            serviceIntent.putExtra("route",1)
            activity?.startForegroundService(serviceIntent)
        }
        binding.btBad.setOnClickListener {
            val uriStr = "https://www.google.com/maps/dir/?api=1&destination=${sharedViewModel.spotLatitude},${sharedViewModel.spotLongitude}"
            val uri = Uri.parse(uriStr)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
            findNavController().navigate(R.id.action_naviDetourEvaluation_to_naviNavigating)
            val serviceIntent = Intent(requireActivity(), NotificationService::class.java)
            serviceIntent.putExtra("route",1)
            activity?.startForegroundService(serviceIntent)
        }
        return view
    }
}