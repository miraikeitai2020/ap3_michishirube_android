package com.example.michishirube.ui.naviTimeSelect

import androidx.lifecycle.ViewModel

class NaviTimeSelectViewModel:ViewModel() {

    var hour = 0
    var minute = 5
    //var time = 0

    fun timeSet(){
        //time = hour * 60 + minute で返したーい
    }

    //あわよくば違うページに行って戻ってきてもデータ保持されるようにしたいね〜
}