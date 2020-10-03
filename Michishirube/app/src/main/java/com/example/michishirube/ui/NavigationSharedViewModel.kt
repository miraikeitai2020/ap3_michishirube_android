package com.example.michishirube.ui

import androidx.lifecycle.ViewModel

class NavigationSharedViewModel: ViewModel() {

    //emotionSelect
    var emotion = 0

    //timeSelect
    var hour = 0
    var minute = 10
    var sumMinute = 0//所要時間

    //naviDestination
    var deviceLatitude = 0.0
    var deviceLongitude = 0.0
    var spotName = ""


    //emotionSelect
    fun setEmotionType(selectedEmotion: Int){
        emotion = selectedEmotion
    }

    //timeSelect
    fun setTime(selectedHour:Int, selectedMinute:Int){
        hour = selectedHour
        minute = selectedMinute
        sumMinute = hour * 60 + minute
    }

    fun setLocation(latitude: Double?, longitude: Double?) {
        deviceLatitude = latitude!!
        deviceLongitude = longitude!!
    }

    fun loadDestination(){//もしかしたらここら辺はちゃんとそれらの（？）ViewModelで書くかも
        //Coroutinesを使用して，Repositryの関数を使って，目的地名を持ってくる
        //withContextでここの目的地名のテキスト（spotName）に値入れて，Fragmentの方でFragmentの方の目的地名（レイアウトと直結してる方）に値追加かな
    }

    //naviDestination
    fun intentDestination(){
        //「ここにいく」を押したら，緯度経度入れてGoogleMapに遷移するあれをしたいね〜
    }

    //naviEvaluation 2ndスプリント
    fun postEvaluate(){//もしかしたらここら辺はちゃんとそれらの（？）ViewModelで書くかも
        //たぶんここでコルーチン
    }

}