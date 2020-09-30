package com.example.michishirube.ui.spotList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentSpotListBinding
import com.example.michishirube.ui.RecyclerSpotAdapter
import kotlinx.android.synthetic.main.fragment_navi_emotion_select.view.*
import kotlinx.android.synthetic.main.fragment_spot_list.view.*


class SpotListFragment : Fragment() {

    private val spotListViewModel: SpotListViewModel by viewModels()
    private lateinit var binding: FragmentSpotListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSpotListBinding.inflate(inflater, container, false)

        binding.lvSpot.adapter = RecyclerSpotAdapter()

        val layout = LinearLayoutManager(context)
        binding.lvSpot.layoutManager = layout

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddSpot.setOnClickListener{
            findNavController().navigate(R.id.action_spotList_to_spotRegister)
        }

    }

}