package com.example.prison

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.PrisonerResponse
import models.PrisonersResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api

class PrisonerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prisoner)


        val prisoner_id = getIntent().getStringExtra("prisoner_id").toString().toInt()
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


        retrofit.getPrisoner("Bearer " + token,prisoner_id).enqueue(object : Callback<PrisonerResponse> {
            override fun onResponse(call: Call<PrisonerResponse>, response: Response<PrisonerResponse>) {
                println(response.body())

                val name = findViewById<TextView>(R.id.name)
                val username = findViewById<TextView>(R.id.username)
                val address = findViewById<TextView>(R.id.address)
                val pesel = findViewById<TextView>(R.id.pesel)
                val idcell = findViewById<TextView>(R.id.idcell)

                name.text = response.body()?.name
                username.text = response.body()?.forname
                address.text = response.body()?.address
                pesel.text = response.body()?.pesel
                idcell.text = response.body()?.idCell.toString()
            }

            override fun onFailure(call: Call<PrisonerResponse>, t: Throwable) {
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