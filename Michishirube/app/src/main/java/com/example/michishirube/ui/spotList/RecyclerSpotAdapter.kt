package com.example.michishirube.ui.spotList

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
        val holder = RecyclerViewHolder(mView)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 7//Listの表示される数がここで決まる〜
    }
}