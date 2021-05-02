package com.example.prison

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.CellItem
import models.LogsResponse
import models.PrisonerResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api


class HomeAcitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_acitivity)


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


        retrofit.getPrisoners("Bearer " + token).enqueue(object :
            Callback<List<PrisonerResponse>> {
            override fun onResponse(call: Call<List<PrisonerResponse>>, response: Response<List<PrisonerResponse>>) {
                val recycler = findViewById<RecyclerView>(R.id.recycler_view_home)

                recycler.adapter = response.body()?.let { PrisonerAdapter(it) }
                recycler.layoutManager = LinearLayoutManager(applicationContext)
                recycler.setHasFixedSize(true)
            }

            override fun onFailure(call: Call<List<PrisonerResponse>>, t: Throwable) {
                println("onFailure")
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