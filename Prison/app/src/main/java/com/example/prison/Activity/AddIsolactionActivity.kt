package com.example.prison.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.addIsolationResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api

class AddIsolactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_isolaction)

        val id = intent.extras
        println("Add Iso: $id")
        val addIsoBtn = findViewById<Button>(R.id.addIsolationBtn)

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

        addIsoBtn.setOnClickListener {
            println("addIsoBtn")
            Toast.makeText(this, "addIsoBtn", Toast.LENGTH_SHORT).show()

            val start = findViewById<TextView>(R.id.addPrisonerIsolationStartData).text.toString()
            val end = findViewById<TextView>(R.id.addPrisonerIsolationEndData).text.toString()

            retrofit.addIsolation("Bearer $token",intent.getStringExtra("prisoner_id").toString().toInt(),start,end).enqueue(object: Callback<addIsolationResponse>{
                override fun onResponse(call: Call<addIsolationResponse>,response: Response<addIsolationResponse>)
                {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<addIsolationResponse>, t: Throwable)
                {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}