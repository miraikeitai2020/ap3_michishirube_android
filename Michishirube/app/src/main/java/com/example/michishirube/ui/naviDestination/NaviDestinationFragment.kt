package com.example.michishirube.ui.naviDestination

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentNaviDestinationBinding
import com.example.michishirube.ui.NavigationSharedViewModel


class NaviDestinationFragment : Fragment(), LocationListener {
    private lateinit var binding: FragmentNaviDestinationBinding
    private val viewModel: NavigationSharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNaviDestinationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //ここにいくボタンを押下
        binding.btGo.setOnClickListener{
            findNavController().navigate(R.id.action_naviDestination_to_naviNavigating)
        }

//        binding.btReload.setOnClickListener {
//            //更新ボタン
//        }
    }

    override fun onLocationChanged(location: Location) {
        //
    }

}