package com.example.michishirube.ui.naviEmotionSelect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentNaviEmotionSelectBinding
import com.example.michishirube.ui.NavigationSharedViewModel


class NaviEmotionSelectFragment : Fragment() {
    private lateinit var binding: FragmentNaviEmotionSelectBinding
    private val sharedViewModel: NavigationSharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNaviEmotionSelectBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.startGPS(requireActivity())
        //感情ごとの数字を代入して次にいく（以下はまだ確定じゃない）
        //幸せ　０
        //普通　１
        //怒り・嫌悪　２
        //ショック　３

        fun emotionBtClickListener(emotion: Int) {
            sharedViewModel.setEmotionType(emotion)
            findNavController().navigate(R.id.action_naviEmotionSelect_to_naviTimeSelect)
        }

        binding.btHappy.setOnClickListener{ emotionBtClickListener(0)}
        binding.btNormally.setOnClickListener { emotionBtClickListener(1) }
        binding.btAngerAversion.setOnClickListener { emotionBtClickListener(2) }
        binding.btShock.setOnClickListener { emotionBtClickListener(3) }

    }

}