package com.example.michishirube.ui.title

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.app.ActivityCompat
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private lateinit var binding: FragmentTitleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTitleBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkRequestPermission()

        //ナビゲーションボタン押下
        binding.ibNavigation.setOnClickListener {
            findNavController().navigate(R.id.action_title_to_naviEmotionSelect)
        }

        //スポット一覧ボタン押下
        binding.ibSpotList.setOnClickListener {
            findNavController().navigate(R.id.action_title_to_spotList)
        }

        //左下の戻るボタン押下したらアプリ終了
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            activity?.finish()
        }
    }

    private fun checkRequestPermission(){
        if(ActivityCompat.checkSelfPermission(requireContext(),android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
            ActivityCompat.requestPermissions(requireActivity(),permissions,1000)
        }
    }
}