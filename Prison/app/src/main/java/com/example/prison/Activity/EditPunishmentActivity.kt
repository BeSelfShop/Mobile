package com.example.prison.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.editPunishmentResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api

class EditPunishmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_punishment)

        val id = intent.extras
        println("Edit Punishment: $id")

        val editPunishmentBtn = findViewById<Button>(R.id.editPunishmentBtn)

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

        editPunishmentBtn.setOnClickListener {
            println("editPunishmentBtn")
            Toast.makeText(this, "editPunishmentBtn", Toast.LENGTH_SHORT).show()

            val start = findViewById<TextView>(R.id.editPrisonerPunishmentStartData).text.toString()
            val end = findViewById<TextView>(R.id.editPrisonerPunishmentEndData).text.toString()

            retrofit.editPunishment("Bearer $token", intent.getStringExtra("prisoner_id").toString().toInt(),intent.getStringExtra("prisoner_id").toString().toInt(),0,start,end).enqueue(object: Callback<editPunishmentResponse>{
                override fun onResponse(call: Call<editPunishmentResponse>, response: Response<editPunishmentResponse>) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<editPunishmentResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }
    }
}