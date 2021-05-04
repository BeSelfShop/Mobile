package com.example.prison.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.RegisterReq
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api

class RegisterAcitivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_acitivity)

        val button = findViewById<Button>(R.id.regButton)

        button.setOnClickListener{

            val username = findViewById<TextView>(R.id.regUserName).text.toString()
            val email = findViewById<TextView>(R.id.regEmail).text.toString()
            val password = findViewById<TextView>(R.id.regPassword).text.toString()
            val name = findViewById<TextView>(R.id.regName).text.toString()
            val forname = findViewById<TextView>(R.id.regForName).text.toString()
            val invCode = findViewById<TextView>(R.id.regInvCode).text.toString()


            val client = OkHttpClient()
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val retrofit: Api = Retrofit.Builder()
                .baseUrl("https://wiezienie202l.azurewebsites.net/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)


            retrofit.userRegister(username,email,password,name,forname,invCode).enqueue(
                object : Callback<RegisterReq> {
                    override fun onResponse(call: Call<RegisterReq>,response: Response<RegisterReq>)
                    {
                        startActivity(Intent(this@RegisterAcitivity, LoginActivity::class.java))
                        overridePendingTransition(0,0)
                    }

                    override fun onFailure(call: Call<RegisterReq>, t: Throwable) {
                        Toast.makeText(this@RegisterAcitivity, "nie Dziala RegisterAcitivity", Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }
}