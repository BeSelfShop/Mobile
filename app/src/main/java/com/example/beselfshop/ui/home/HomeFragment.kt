package com.example.beselfshop.ui.home

import Adapter.MyCustomAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beselfshop.R
import kotlin.random.Random

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val list = root.findViewById<ListView>(R.id.shopItem) as RecyclerView

        val product = generateDummyList(9)

        val adapter = MyCustomAdapter(product)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this.context)

        return root
    }

    private fun generateDummyList(size: Int): ArrayList<Product> {
        val list = ArrayList<Product>()
        for (i in 1..size) {
            val price = Random.nextInt(1000, 15000)
            val item = Product("Product  $i", price.toString() + " zł", R.drawable.product)
            list += item
        }
        return list
    }



}
