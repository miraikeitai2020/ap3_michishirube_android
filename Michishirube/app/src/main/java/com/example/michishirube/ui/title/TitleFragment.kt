package com.example.michishirube.ui.title

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import kotlinx.android.synthetic.main.fragment_title.view.*
import java.util.jar.Manifest

class TitleFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_title, container, false)

        //押されたら
        view.ibNavigation.setOnClickListener {
            findNavController().navigate(R.id.action_title_to_naviEmotionSelect)
        }
        view.ibSpotList.setOnClickListener {
            findNavController().navigate(R.id.action_title_to_spotList)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 1000) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("debug", "checkSelfPermission true")

            }
        }
    }

}