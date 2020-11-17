package com.example.michishirube.ui.spotList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SpotListViewModelFactory () : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SpotListViewModel::class.java)){
            return SpotListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModell class")
    }
}