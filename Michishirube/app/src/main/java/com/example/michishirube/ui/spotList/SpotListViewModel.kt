package com.example.michishirube.ui.spotList

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.michishirube.DI
import com.example.michishirube.data.Repository
import com.example.michishirube.models.Spot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpotListViewModel(context: Context): ViewModel() {
    private val spotRepository: Repository
    val spotList: LiveData<List<Spot>>

    init {
        spotRepository = DI.injectRepository(context)
        spotList = spotRepository.loadAllSpots()
    }

    fun loadAllSpot(){
        viewModelScope.launch {
            val spotData = spotRepository.loadAllSpots()
        }
    }
    fun registerSpot(spot: Spot) = viewModelScope.launch(Dispatchers.IO) {
        spotRepository.inputSpot(spot)

    }
}