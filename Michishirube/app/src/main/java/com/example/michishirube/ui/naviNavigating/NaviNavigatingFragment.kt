package com.example.michishirube.ui.naviNavigating

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import kotlinx.android.synthetic.main.fragment_navi_navigating.view.*

class NaviNavigatingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_navi_navigating, container, false)
        view.ibNavigating.setOnClickListener{
            findNavController().navigate(R.id.action_naviNavigating_to_naviEvaluation)
        }
        return view
    }
}