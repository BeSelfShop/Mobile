package com.example.prison.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.addCellResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api

class AddCellActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_cell)


        val addCellbtn = findViewById<Button>(R.id.addCellBtn)

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

        addCellbtn.setOnClickListener {

            val beds = findViewById<TextView>(R.id.addCellBedsCount).toString().toInt()
            val cellNumber = findViewById<TextView>(R.id.addCellCellNumber).toString()
            val idType = findViewById<TextView>(R.id.addCellIdCellType).toString().toInt()

            retrofit.addCell("Bearer $token",beds,idType,cellNumber).enqueue(object: Callback<addCellResponse>{
                override fun onResponse(call: Call<addCellResponse>, response: Response<addCellResponse>) {
                    println(response.body())
                    startActivity(Intent(this@AddCellActivity,CellsActivity::class.java))
                    overridePendingTransition(0,0)
                }

                override fun onFailure(call: Call<addCellResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }

    }
}