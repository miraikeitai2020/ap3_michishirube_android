package com.example.michishirube.ui.spotList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentSpotListBinding
import com.example.michishirube.models.Spot


class SpotListFragment : Fragment() {

    private val spotListViewModel: SpotListViewModel by viewModels{
        SpotListViewModelFactory(this.requireContext())
    }
    private lateinit var binding: FragmentSpotListBinding

    lateinit var adapter: RecyclerSpotAdapter
    lateinit var manager: FragmentManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSpotListBinding.inflate(inflater, container, false)

        val layout = LinearLayoutManager(context)
        binding.lvSpot.layoutManager = layout
        binding.lvSpot.adapter = RecyclerSpotAdapter()
        binding.viewModel = spotListViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manager = activity?.supportFragmentManager!!

        spotListViewModel.spotList.observe(viewLifecycleOwner, Observer {
            val adapter = binding.lvSpot.adapter as RecyclerSpotAdapter?
            adapter?.setSpot(it)
        })

        binding.lifecycleOwner = this.viewLifecycleOwner

        binding.fabAddSpot.setOnClickListener{
//            val spot = Spot(0,"",0,"",0.0,0.0)
            findNavController().navigate(R.id.action_spotList_to_spotRegister)
        }

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            findNavController().navigate(R.id.titleFragment)
        }

    }

}