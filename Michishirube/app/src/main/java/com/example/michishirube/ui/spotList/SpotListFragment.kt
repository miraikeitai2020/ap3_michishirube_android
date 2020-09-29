package com.example.michishirube.ui.spotList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import kotlinx.android.synthetic.main.fragment_navi_emotion_select.view.*
import kotlinx.android.synthetic.main.fragment_spot_list.view.*


class SpotListFragment : Fragment() {

    private val spotListViewModel: SpotListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_spot_list, container, false)


        view.fabAddSpot.setOnClickListener{
            findNavController().navigate(R.id.action_spotList_to_spotRegister)
        }
        return view
    }

}