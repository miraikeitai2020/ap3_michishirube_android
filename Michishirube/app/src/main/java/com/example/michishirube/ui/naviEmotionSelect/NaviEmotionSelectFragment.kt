package com.example.michishirube.ui.naviEmotionSelect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentNaviEmotionSelectBinding
import kotlinx.android.synthetic.main.fragment_navi_emotion_select.view.*


class NaviEmotionSelectFragment : Fragment() {
    private lateinit var binding: FragmentNaviEmotionSelectBinding
    private val naviEmotionSelectViewModel: NaviEmotionSelectViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNaviEmotionSelectBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btHappy.setOnClickListener{
            findNavController().navigate(R.id.action_naviEmotionSelect_to_naviTimeSelect)
        }
        binding.btNormally.setOnClickListener {
            findNavController().navigate(R.id.action_naviEmotionSelect_to_naviTimeSelect)
        }
        binding.btAngerAversion.setOnClickListener {
            findNavController().navigate(R.id.action_naviEmotionSelect_to_naviTimeSelect)
        }
        binding.btShock.setOnClickListener {
            findNavController().navigate(R.id.action_naviEmotionSelect_to_naviTimeSelect)
        }
    }

}