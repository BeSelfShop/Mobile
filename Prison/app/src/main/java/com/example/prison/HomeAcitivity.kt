package com.example.prison

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.CellItem

class HomeAcitivity : AppCompatActivity(),CellAdapter.OnItemClickListener {
    val cellItem = generateDummyList(100)
    private val adapter = CellAdapter(cellItem,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_acitivity)


        val recycler = findViewById<RecyclerView>(R.id.recycler_view)

        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)

    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Image $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = cellItem[position]
        clickedItem.number += "Clicked"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList(size: Int) : List<CellItem>
    {
        val list = ArrayList<CellItem>()

        for (i in 0 until size)
        {
            if (i%2 == 0)
            {
                list.add(CellItem("number "+ (i+1),"John"))
            }
            else
            {
                list.add(CellItem("number "+ (i+1),"George"))
            }

        }

        return list
    }


}