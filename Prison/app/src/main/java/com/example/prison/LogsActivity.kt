package com.example.prison

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.LogsResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api
import tools.RetrofitClient


class LogsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs)
        println("start")

    }

    override fun onStart() {
        super.onStart()
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

        retrofit.getLogs("Bearer " + token).enqueue(object :
            Callback<List<LogsResponse>> {
            override fun onResponse(call: Call<List<LogsResponse>>, response: Response<List<LogsResponse>>) {

                print(response.body())
            }

            override fun onFailure(call: Call<List<LogsResponse>>, t: Throwable) {
                println("onFailure")
            }

        })



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.home -> {
                startActivity(Intent(this, HomeAcitivity::class.java))
                overridePendingTransition(0, 0)
            }
            R.id.profile -> {
                startActivity(Intent(this, ProfileAcitivity::class.java))
                overridePendingTransition(0, 0)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}