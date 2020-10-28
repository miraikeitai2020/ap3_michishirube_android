package com.example.michishirube.ui.NaviEmoValueSelect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentNaviEmoValueSelectBinding
import com.google.android.material.slider.Slider
import java.text.NumberFormat

class NaviEmoValueSelectFragment : Fragment() {
    private lateinit var binding:FragmentNaviEmoValueSelectBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNaviEmoValueSelectBinding.inflate(inflater, container, false)
        binding.tvSelectedEmotion =
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sbEmoValue.addOnChangeListener{ slider, value, fromUser ->
            //
        }
        binding.ibEmoValueDecision.setOnClickListener {
            findNavController().navigate(R.id.action_naviEmoValueSelect_to_naviTimeSelect)
        }
    }

}