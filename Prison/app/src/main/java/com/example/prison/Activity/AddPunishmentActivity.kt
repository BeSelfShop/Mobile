package com.example.prison.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.addPunishmentResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api

class AddPunishmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_punishment)

        val id = intent.extras
        println("Add Pass: $id")

        val addPunish = findViewById<Button>(R.id.addPunishmentBtn)

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

        addPunish.setOnClickListener {
            println("addPunish")
            Toast.makeText(this, "addPunish", Toast.LENGTH_SHORT).show()

            val start = findViewById<TextView>(R.id.addPrisonerPunishmentStartData).text.toString()
            val end = findViewById<TextView>(R.id.addPrisonerPunishmentStartData).text.toString()

            retrofit.addPunishment("Bearer $token",intent.getStringExtra("prisoner_id").toString().toInt(),0,start,end).enqueue(object: Callback<addPunishmentResponse>
            {
                override fun onResponse(call: Call<addPunishmentResponse>,response: Response<addPunishmentResponse>) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<addPunishmentResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}