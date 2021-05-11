package com.example.prison.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.PrisonerResponse
import models.editPrisonerResponser
import okhttp3.OkHttpClient
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import tools.Api

class EditPrisonerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_prisoner)

        val editPrisonBtn = findViewById<Button>(R.id.editPrisonerButton)

        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val retrofit: Api = Retrofit.Builder()
            .baseUrl("https://wiezienie202l.azurewebsites.net/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        val id = intent.getStringExtra("prisoner_id").toString().toInt()
        println(id)
        val pref = getApplicationContext().getSharedPreferences("my_shared_preff", 0);
        val token = pref.getString("tokken", "")


        editPrisonBtn.setOnClickListener {
            val name = findViewById<TextView>(R.id.editPrisonerName).text.toString()
            val forname = findViewById<TextView>(R.id.editPrisonerForname).text.toString()
            val pesel = findViewById<TextView>(R.id.editPrisonerPesel).text.toString()
            val address = findViewById<TextView>(R.id.editPrisonerAdress).text.toString()
            val idCell = findViewById<TextView>(R.id.editPrisonerIdCell).text.toString().toInt()

            Toast.makeText(this, name + " " + forname + " " + pesel + " " + address + " " + idCell, Toast.LENGTH_SHORT).show()
            retrofit.updatePrisoner("Bearer " + token,id,name,forname,pesel,address,idCell).enqueue(object :
                Callback<editPrisonerResponser> {
                override fun onResponse(call: Call<editPrisonerResponser>,response: Response<editPrisonerResponser>) {
                    println(response.body())
                }

                override fun onFailure(call: Call<editPrisonerResponser>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }
    }
}