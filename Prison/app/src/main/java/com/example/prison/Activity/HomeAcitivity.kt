package com.example.prison.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prison.Adapter.PrisonerAdapter
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.PrisonersResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api


class HomeAcitivity : AppCompatActivity(), PrisonerAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_acitivity)


        val pref = getApplicationContext().getSharedPreferences("my_shared_preff", 0);
        val token = pref.getString("tokken", "")

        val buttonAdd = findViewById<Button>(R.id.addPrisoner)


        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val retrofit: Api = Retrofit.Builder()
            .baseUrl("https://wiezienie202l.azurewebsites.net/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)




        retrofit.getPrisoners("Bearer " + token).enqueue(object : Callback<List<PrisonersResponse>> {
            override fun onResponse(call: Call<List<PrisonersResponse>>, response: Response<List<PrisonersResponse>>) {
                response.body()?.let { initRecyclerView(it) }
            }

            override fun onFailure(call: Call<List<PrisonersResponse>>, t: Throwable) {
                Toast.makeText(this@HomeAcitivity, "nie Dziala HomeAcitivity", Toast.LENGTH_SHORT).show()
            }

        })

        buttonAdd.setOnClickListener{

            startActivity(Intent(this, AddPrisonerrActivity::class.java))
            overridePendingTransition(0,0)
        }

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
            R.id.cell -> {
                startActivity(Intent(this, CellsActivity::class.java))
                overridePendingTransition(0,0)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerView(list: List<PrisonersResponse>)
    {
        val recycler = findViewById<RecyclerView>(R.id.recycler_view_home)
        recycler.adapter =  PrisonerAdapter(list,this)
        recycler.layoutManager = LinearLayoutManager(applicationContext)
        recycler.setHasFixedSize(true)
    }

    override fun onItemClick(position: Int) {
        val item = findViewById<RecyclerView>(R.id.recycler_view_home).get(position)
        val id_item = item.findViewById<TextView>(R.id.prisoner_number).text.toString()
        startActivity(Intent(this, PrisonerActivity::class.java).putExtra("prisoner_id",id_item))
        overridePendingTransition(0,0)
    }
}