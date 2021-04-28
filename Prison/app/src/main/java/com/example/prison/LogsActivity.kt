package com.example.prison

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import models.LogsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tools.Api
import tools.RetrofitClient

class LogsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logs)


        val pref = getApplicationContext().getSharedPreferences("my_shared_preff", 0);

        println("1.Logs")
        val token = pref.getString("tokken","")
        println(token)

            RetrofitClient.buildApi(Api::class.java).getLogs(token).enqueue(object : Callback<List<LogsResponse>>
            {
                override fun onResponse(call: Call<List<LogsResponse>>,response: Response<List<LogsResponse>>) {

                    print(response)
                }

                override fun onFailure(call: Call<List<LogsResponse>>, t: Throwable) {
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
}