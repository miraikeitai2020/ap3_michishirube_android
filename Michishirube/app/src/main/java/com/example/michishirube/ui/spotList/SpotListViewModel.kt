package com.example.michishirube.ui.spotList

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.michishirube.data.SpotRepository
import com.example.michishirube.data.local.db.SpotDatabase
import com.example.michishirube.data.local.db.SpotsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpotListViewModel(context: Context): ViewModel() {

    private val repository: SpotRepository
    val spotList: LiveData<List<SpotsEntity>>

    init{
        val spotsDao = SpotDatabase.getDatabase(context).registeredSpotsDao()
        repository = SpotRepository(spotsDao)
        spotList = repository.allSpots
    }


    fun insert(spot: SpotsEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(spot)
    }


}