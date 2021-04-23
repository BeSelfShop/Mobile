package com.example.prison

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import models.LogsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tools.RetrofitClient

class LogsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs)


        val bottom_nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottom_nav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener
        { item ->
            when (item.itemId) {
                R.id.home -> {
                    val intent = Intent(applicationContext, HomeAcitivity::class.java)
                    startActivity(intent)
                }
                R.id.logs -> {
                    val intent = Intent(applicationContext, LogsActivity::class.java)
                    startActivity(intent)
                }
                R.id.profile -> {
                    val intent = Intent(applicationContext, ProfileActivity::class.java)
                    startActivity(intent)
                }

            }
            false
        })

        val pref = getApplicationContext().getSharedPreferences("my_shared_preff", 0);

        val token = pref.getString("tokken","")
        println(token)

            RetrofitClient.instance.getLogs(token).enqueue(object : Callback<List<LogsResponse>>
            {
                override fun onResponse(call: Call<List<LogsResponse>>,response: Response<List<LogsResponse>>) {

                    print(response.body())
                }

                override fun onFailure(call: Call<List<LogsResponse>>, t: Throwable) {
                    println("onFailure")
                }

            })
    }
}