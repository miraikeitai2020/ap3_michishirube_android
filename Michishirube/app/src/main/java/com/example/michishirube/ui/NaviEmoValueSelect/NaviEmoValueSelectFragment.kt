package com.example.michishirube.ui.NaviEmoValueSelect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentNaviEmoValueSelectBinding
import com.example.michishirube.ui.NavigationSharedViewModel

class NaviEmoValueSelectFragment : Fragment() {
    private val sharedViewModel: NavigationSharedViewModel by activityViewModels()
    private lateinit var binding:FragmentNaviEmoValueSelectBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNaviEmoValueSelectBinding.inflate(inflater, container, false)
        binding.tvSelectedEmotion.text = when(sharedViewModel.emotion){
            0 -> "幸せ"
            1 -> "穏やか"
            2 -> "怒り"
            3 -> "悲しみ"
            else -> ""
        }
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