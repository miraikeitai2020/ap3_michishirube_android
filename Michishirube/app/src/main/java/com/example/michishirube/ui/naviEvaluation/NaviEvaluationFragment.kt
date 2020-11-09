package com.example.michishirube.ui.naviEvaluation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.michishirube.NotificationService
import com.example.michishirube.R
import kotlinx.android.synthetic.main.fragment_navi_evaluation.view.*

class NaviEvaluationFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val intent = Intent(activity, NotificationService::class.java)
        activity?.stopService(intent)
        val view = inflater.inflate(R.layout.fragment_navi_evaluation, container, false)
        view.btGood.setOnClickListener {
            findNavController().navigate(R.id.action_naviEvaluation_to_title)
        }
        view.btBad.setOnClickListener {
            findNavController().navigate(R.id.action_naviEvaluation_to_title)
        }
        return view
    }

}