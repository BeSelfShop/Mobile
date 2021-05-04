package com.example.prison.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.CellDetails
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api

class CellDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cell_details)
        val cell_id = getIntent().getStringExtra("cell_id").toString().toInt()

        val pref = getApplicationContext().getSharedPreferences("my_shared_preff", 0);
        val token = pref.getString("tokken", "")

        val edytujCele = findViewById<Button>(R.id.edytujCele)
        val usunCele = findViewById<Button>(R.id.usunCele)

        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val retrofit: Api = Retrofit.Builder()
            .baseUrl("https://wiezienie202l.azurewebsites.net/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        retrofit.getCellDetails("Bearer " + token,cell_id).enqueue(object : Callback<CellDetails> {
            override fun onResponse(call: Call<CellDetails>, response: Response<CellDetails>) {
                println(response.body())

                val bedsCount = findViewById<TextView>(R.id.bedsCountCellDetails)
                val cellNumber = findViewById<TextView>(R.id.numberCellDetails)
                val id = findViewById<TextView>(R.id.idCellDetails)
                val idCellType = findViewById<TextView>(R.id.cellTypeDetails)
                val idPrison = findViewById<TextView>(R.id.idPrisonCellDetails)
                val occupiedBeds = findViewById<TextView>(R.id.occupiedBedsCellDetails)


                bedsCount.text = response.body()?.bedsCount.toString()
                cellNumber.text = response.body()?.cellNumber.toString()
                id.text = response.body()?.id.toString()
                idCellType.text = response.body()?.idCellType.toString()
                idPrison.text = response.body()?.idPrison.toString()
                occupiedBeds.text = response.body()?.occupiedBeds.toString()
            }

            override fun onFailure(call: Call<CellDetails>, t: Throwable) {
                Toast.makeText(this@CellDetailsActivity, "nie Dziala CellDetails", Toast.LENGTH_SHORT).show()
            }

        })

        edytujCele.setOnClickListener {
            Toast.makeText(this, "EdytujCele", Toast.LENGTH_SHORT).show()
        }

        usunCele.setOnClickListener {
            Toast.makeText(this, "usun Cele", Toast.LENGTH_SHORT).show()
        }

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
            R.id.cell -> {
                startActivity(Intent(this, CellsActivity::class.java))
                overridePendingTransition(0,0)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}