package com.example.michishirube.ui.spotRegister

import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.michishirube.R
import com.example.michishirube.databinding.FragmentSpotRegisterBinding


class SpotRegisterFragment : Fragment() {
    private val spotRegisterViewModel: SpotRegisterViewModel by viewModels()
    private lateinit var binding: FragmentSpotRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpotRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ibDecision.setOnClickListener {
            findNavController().navigate(R.id.action_spotRegister_to_spotList)
        }

        binding.ivSpot.setOnClickListener{
            selectImage()
        }
    }

    private fun selectImage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }

        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    companion object {
        private const val READ_REQUEST_CODE: Int = 42
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
                        val imageView = binding.ivSpot.findViewById<ImageView>(R.id.ivSpot)
                        imageView.setImageBitmap(image)
                    }
                } catch (e: Exception) {
                    //エラー発生時の動作(toastとか？)
                }
            }
        }
    }
}