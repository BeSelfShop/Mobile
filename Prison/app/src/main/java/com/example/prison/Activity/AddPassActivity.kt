package com.example.prison.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.addPassResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api

class AddPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pass)

        val id = intent.extras
        println("Add Pass: $id")

        val addPass = findViewById<Button>(R.id.addPassBtn)

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

        addPass.setOnClickListener {
            println("addPass")
            Toast.makeText(this, "addPass", Toast.LENGTH_SHORT).show()

            val start = findViewById<TextView>(R.id.addPrisonerPassStartData).text.toString()
            val end = findViewById<TextView>(R.id.addPrisonerPassEndData).text.toString()

            retrofit.addPass("Bearer $token",intent.getStringExtra("prisoner_id").toString().toInt(),start,end).enqueue(object: Callback<addPassResponse>{
                override fun onResponse(call: Call<addPassResponse>,response: Response<addPassResponse>) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<addPassResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}