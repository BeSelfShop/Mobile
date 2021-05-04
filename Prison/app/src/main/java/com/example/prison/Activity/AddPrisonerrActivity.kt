package com.example.prison.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prison.Adapter.PrisonerAdapter
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.PrisonersResponse
import models.addPrisoner
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api

class AddPrisonerrActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_prisonerr)

        val pref = getApplicationContext().getSharedPreferences("my_shared_preff", 0);
        val token = pref.getString("tokken", "")
        val buttonAdd = findViewById<Button>(R.id.addPri)

        buttonAdd.setOnClickListener{

            val name = findViewById<TextView>(R.id.addPrisonerName).text.toString()
            val forname = findViewById<TextView>(R.id.addPrisonerForname).text.toString()
            val pesel =  findViewById<TextView>(R.id.addPrisonerPesel).text.toString()
            val adress = findViewById<TextView>(R.id.addPrisonerAdress).text.toString()



            val client = OkHttpClient()
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val retrofit: Api = Retrofit.Builder()
                .baseUrl("https://wiezienie202l.azurewebsites.net/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)

            retrofit.addPrisoner("Bearer " + token,name,forname,pesel,adress).enqueue(object: Callback<addPrisoner>{
                override fun onResponse(call: Call<addPrisoner>, response: Response<addPrisoner>) {
                    startActivity(Intent(this@AddPrisonerrActivity,HomeAcitivity::class.java))
                    overridePendingTransition(0,0)
                }

                override fun onFailure(call: Call<addPrisoner>, t: Throwable) {
                    Toast.makeText(this@AddPrisonerrActivity, "nie Dziala AddPrisonerrActivity", Toast.LENGTH_SHORT).show()
                }

            })

        }



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
            R.id.cell -> {
                startActivity(Intent(this, CellsActivity::class.java))
                overridePendingTransition(0,0)
            }
        }

        return super.onOptionsItemSelected(item)
    }

}