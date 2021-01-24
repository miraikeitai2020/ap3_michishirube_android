package com.example.michishirube.ui.spotList

import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import com.example.michishirube.data.local.db.SpotsEntity
import com.example.michishirube.databinding.FragmentSpotRegisterBinding


class SpotRegisterFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private val spotListViewModel: SpotListViewModel by viewModels<SpotListViewModel>{
        SpotListViewModelFactory(requireContext())
    }
    private lateinit var binding: FragmentSpotRegisterBinding

    private var emotion = 0

    companion object {
        private const val READ_REQUEST_CODE: Int = 42
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSpotRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ibDecision.setOnClickListener {
            val name = binding.etSpotName.text.toString()
            val desc = binding.etExp.text.toString()
            val spot = SpotsEntity(0,name,emotion,desc,0.0,0.0)
            spotListViewModel.insert(spot)
            findNavController().navigate(R.id.action_spotRegister_to_spotList)
        }

        binding.ivSpot.setOnClickListener {
            selectImage()
        }



    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        val cr: ContentResolver? = requireContext().contentResolver
        if (resultCode != RESULT_OK) {
            return
        }
        when (requestCode) {
            READ_REQUEST_CODE -> {
                try {
                    resultData?.data?.also { uri ->
                        val inputStream = cr?.openInputStream(uri)
                        val image = BitmapFactory.decodeStream(inputStream)
                        binding.ivSpot.setImageBitmap(image)
                    }
                } catch (e: Exception) {
                    //エラー発生時の動作(toastとか？)
                }
            }
        }
    }


    private fun selectImage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val emoStr = parent?.getItemAtPosition(position) as String

        when(emoStr){
            "ハッピー" -> emotion = 0
            "おだやか" -> emotion = 1
            "わくわく" -> emotion = 2
            "しんみり" -> emotion = 3
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}