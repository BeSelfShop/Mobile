package com.example.prison

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.logs -> {
                startActivity(Intent(this, LogsActivity::class.java))
                overridePendingTransition(0,0)
            }
            R.id.profile -> {
                startActivity(Intent(this, ProfileAcitivity::class.java))
                overridePendingTransition(0,0)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun generateDummyList(size: Int) : List<CellItem>
    {
        val list = ArrayList<CellItem>()

        for (i in 0 until size)
        {
            if (i%2 == 0)
            {
                list.add(CellItem("number " + (i + 1), "John"))
            }
            else
            {
                list.add(CellItem("number " + (i + 1), "George"))
            }

        }

        return list
    }
}