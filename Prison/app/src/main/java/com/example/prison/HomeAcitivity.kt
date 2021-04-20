package com.example.prison

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.CellItem

class HomeAcitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_acitivity)

        val cellItem = generateDummyList(100)

        val recycler = findViewById<RecyclerView>(R.id.recycler_view)

        recycler.adapter = CellAdapter(cellItem)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)

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