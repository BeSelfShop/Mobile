package com.example.prison

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import storage.SharedPrefManager

class HomeAcitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_acitivity)


        println("1:    " + getSharedPreferences("SHARED_PREF_NAME", Context.MODE_PRIVATE).all)

    }
}