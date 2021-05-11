package com.example.prison.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.editPassResponse
import models.editPunishmentResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api

class EditPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pass)

        val id = intent.extras
        println("Edit Pass: $id")

        val editPassBtn = findViewById<Button>(R.id.editPassBtn)

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

        editPassBtn.setOnClickListener {
            println("editPassBtn")
            Toast.makeText(this, "editPassBtn", Toast.LENGTH_SHORT).show()

            val end = findViewById<TextView>(R.id.editPrisonerPunishmentEndData).text.toString()

            retrofit.editPass("Bearer $token",intent.getStringExtra("prisoner_id").toString().toInt(),end).enqueue(object: Callback<editPassResponse>{
                override fun onResponse(call: Call<editPassResponse>,response: Response<editPassResponse>) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<editPassResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }
}