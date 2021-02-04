package com.example.michishirube.ui.naviDetourEvaluation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import kotlinx.android.synthetic.main.fragment_navi_detour_evaluation.view.*


class naviDetourEvaluationFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =inflater.inflate(R.layout.fragment_navi_detour_evaluation, container, false)
        view.btGood.setOnClickListener {
            findNavController().navigate(R.id.action_naviDetourEvaluation_to_naviNavigating)
        }
        view.btBad.setOnClickListener {
            findNavController().navigate(R.id.action_naviDetourEvaluation_to_naviNavigating)
        }
        return view
    }
}