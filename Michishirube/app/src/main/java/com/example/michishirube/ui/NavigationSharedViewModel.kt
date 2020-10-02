package com.example.michishirube.ui

import android.util.Log
import androidx.lifecycle.ViewModel

class NavigationSharedViewModel: ViewModel() {

    //emotionSelect
    var emotion = 0

    //timeSelect
    var hour = 0
    var minute = 10
    var time = 0//所要時間

    //naviDestination
    var lat = 0.0
    var lon = 0.0
    var spotName = ""


    //emotionSelectは関数多分使わない


    //timeSelect 2ndスプリント
    fun setTime(selectedHour:Int, selectedMinute:Int){
        hour = selectedHour
        minute = selectedMinute
        time = hour * 60 + minute
    }


    //naviDestination
    fun getLocationInfo(){
        //画面遷移してきた時点での位置情報を得る〜
    }
    fun loadDestination(){//もしかしたらここら辺はちゃんとそれらの（？）ViewModelで書くかも
        //Coroutinesを使用して，Repositryの関数を使って，目的地名を持ってくる
        //withContextでここの目的地名のテキスト（spotName）に値入れて，Fragmentの方でFragmentの方の目的地名（レイアウトと直結してる方）に値追加かな
    }

    fun intentDestination(){
        //「ここにいく」を押したら，緯度経度入れてGoogleMapに遷移するあれをしたいね〜
    }


    //naviEvaluation 2ndスプリント
    fun postEvaluate(){//もしかしたらここら辺はちゃんとそれらの（？）ViewModelで書くかも
        //たぶんここでコルーチン
    }

}