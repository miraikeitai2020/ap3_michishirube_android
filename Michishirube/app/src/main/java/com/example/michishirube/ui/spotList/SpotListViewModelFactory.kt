package com.example.michishirube.ui.spotList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SpotListViewModelFactory (private val context: Context) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SpotListViewModel::class.java)){
            return SpotListViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}