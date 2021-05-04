package com.example.prison.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prison.Adapter.CellsAdapter
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.Cell
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api

class CellsActivity : AppCompatActivity(), CellsAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cells)

        val pref = getApplicationContext().getSharedPreferences("my_shared_preff", 0);
        val token = pref.getString("tokken", "")

        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val retrofit: Api = Retrofit.Builder()
            .baseUrl("https://wiezienie202l.azurewebsites.net/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)


        retrofit.getCells("Bearer " + token).enqueue(object :
            Callback<List<Cell>> {
            override fun onResponse(call: Call<List<Cell>>, response: Response<List<Cell>>) {
                println(response.body())
                response.body()?.let { initRecyclerView(it) }
            }

            override fun onFailure(call: Call<List<Cell>>, t: Throwable) {
                Toast.makeText(this@CellsActivity, "nie Dziala CellActivity", Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.home -> {
                startActivity(Intent(this, HomeAcitivity::class.java))
                overridePendingTransition(0,0)
            }
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

    private fun initRecyclerView(list: List<Cell>)
    {
        val recycler = findViewById<RecyclerView>(R.id.recycler_view_cell)
        recycler.adapter =  CellsAdapter(list,this)
        recycler.layoutManager = LinearLayoutManager(applicationContext)
        recycler.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {

        val item = findViewById<RecyclerView>(R.id.recycler_view_cell).get(position)
        val id = item.findViewById<TextView>(R.id.cellId).text.toString()

        startActivity(Intent(this, CellDetailsActivity::class.java).putExtra("cell_id",id))
        overridePendingTransition(0,0)
    }
}