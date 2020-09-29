package com.example.michishirube.ui.spotRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentNaviEmotionSelectBinding


class SpotRegisterFragment : Fragment() {
    //private lateinit var spotRegisterViewModel: SpotRegisterViewModel by viewModels()
    //private lateinit var binding: FragmentNaviEmotionSelectBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_spot_register, container, false)
        //binding = FragmentSpotRegisterBinding.inflate(inflater, container, false)
        //        return binding.root
        return view
    }

}