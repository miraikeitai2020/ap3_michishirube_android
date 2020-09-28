package com.example.michishirube.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.michishirube.R

class RecyclerSpotAdapter(): RecyclerView.Adapter<RecyclerSpotAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInfater = LayoutInflater.from(parent.context)
        val mView = layoutInfater.inflate(R.layout.list_spot,parent,false)//多分ここDataBind使う
        return RecyclerViewHolder(mView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}