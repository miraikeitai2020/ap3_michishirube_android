package com.example.michishirube.ui.naviDestination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import kotlinx.android.synthetic.main.fragment_navi_destination.view.*
import kotlinx.android.synthetic.main.fragment_navi_emotion_select.view.*


class NaviDestinationFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_navi_destination, container, false)
        view.btGo.setOnClickListener{
            findNavController().navigate(R.id.action_naviDestination_to_naviNavigating)
        }
        return view
    }

}