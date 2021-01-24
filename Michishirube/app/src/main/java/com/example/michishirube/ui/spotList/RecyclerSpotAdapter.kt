package com.example.michishirube.ui.spotList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.michishirube.R
import com.example.michishirube.data.local.db.SpotsEntity
import com.example.michishirube.databinding.ListSpotBinding
import com.example.michishirube.models.Spot

class RecyclerSpotAdapter(initList: List<SpotsEntity> = emptyList()): RecyclerView.Adapter<RecyclerSpotAdapter.RecyclerViewHolder>() {

    var spotList: List<SpotsEntity> = initList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInfater = LayoutInflater.from(parent.context)
        val binding: ListSpotBinding = DataBindingUtil.inflate<ListSpotBinding>(LayoutInflater.from(parent.context), R.layout.list_spot, parent, false)

        return RecyclerViewHolder(binding)
    }

    class RecyclerViewHolder(val binding: ListSpotBinding): RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val spot = spotList[position]
        holder.binding.viewModel = Spot(spot.spotId,spot.spotName,spot.spotEmotion,spot.spotDesc,spot.spotLatitude,spot.spotLongitude)

    }

    override fun getItemCount(): Int {
        return spotList.size
    }

    fun setSpot(spot: List<SpotsEntity>){
        this.spotList = spot
        notifyDataSetChanged()

    }


}