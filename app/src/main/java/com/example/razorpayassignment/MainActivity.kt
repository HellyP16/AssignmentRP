package com.example.razorpayassignment

import Pojo.PageData
import Pojo.UiType
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.assignment.JsonFetcher
import com.example.razorpayassignment.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()

    }

    private fun setUpView() {
        val json =
            JsonFetcher().getStringFromUrl("https://demo.ezetap.com/mobileapps/android_assignment.json")
        val pageDataType = object : TypeToken<PageData?>() {}.type
        val dataObj: PageData? = Gson().fromJson(json, pageDataType)

        binding.heading.text = dataObj?.headingText
        Glide.with(this).load(dataObj?.logoUrl).into(binding.logo)

        dataObj?.uidata?.forEach {
            when (it?.uitype) {
                UiType.label.name -> {
                    val inflater =
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val view = inflater.inflate(R.layout.label_root_view, null)
                    view.findViewById<TextView>(R.id.value).text = it.value
                    binding.tempLl.addView(view)
                }
                UiType.edittext.name -> {
                    val inflater =
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val view = inflater.inflate(R.layout.edittext_root_view, null)
                    view.findViewById<EditText>(R.id.input_value).hint = it.hint
                    binding.tempLl.addView(view)
                }
                UiType.button.name -> {
                    binding.btnSubmit.text = it.value
                }
            }
        }
    }
}