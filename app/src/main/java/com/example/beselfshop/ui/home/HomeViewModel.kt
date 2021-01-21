package com.example.beselfshop.ui.home

import android.R
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class HomeViewModel : ViewModel() {

    var simpleList: ListView? = null
    var countryList = arrayOf("India", "China", "australia", "Portugle", "America", "NewZealand")

}