package com.example.prison.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prison.Adapter.PrisonerAdapter
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import models.Cell
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

        var idCell: Int = 0


        val client = OkHttpClient()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val retrofit: Api = Retrofit.Builder()
            .baseUrl("https://wiezienie202l.azurewebsites.net/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)




        retrofit.getCells("Bearer " + token).enqueue(object : Callback<List<Cell>> {
            override fun onResponse(call: Call<List<Cell>>, response: Response<List<Cell>>) {
                val spinnerIdCell = findViewById<Spinner>(R.id.addPriIdCell)

                val listIdCells: MutableList<String> = mutableListOf()

                for (idCellItem in response.body()!!) {
                    val item: String = idCellItem.id.toString()
                    listIdCells.add(item)
                }


                spinnerIdCell.adapter = listIdCells?.let {
                    ArrayAdapter<String>(
                        this@AddPrisonerrActivity,
                        android.R.layout.simple_list_item_1,
                        it
                    )
                }
                spinnerIdCell.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        idCell = parent?.getItemAtPosition(position).toString().toInt()
                    }
                }
            }

            override fun onFailure(call: Call<List<Cell>>, t: Throwable) {
                Toast.makeText(this@AddPrisonerrActivity, "nie dziala id Cells", Toast.LENGTH_SHORT).show()
            }

        })


        buttonAdd.setOnClickListener{

            val name = findViewById<TextView>(R.id.addPrisonerName).text.toString().trim()
            val forname = findViewById<TextView>(R.id.addPrisonerForname).text.toString().trim()
            val pesel =  findViewById<TextView>(R.id.addPrisonerPesel).text.toString().trim()
            val adress = findViewById<TextView>(R.id.addPrisonerAdress).text.toString().trim()
            println(idCell)


            retrofit.addPrisoner("Bearer $token",name,forname,pesel,adress,true,0,true,idCell).enqueue(object: Callback<addPrisoner>{
                override fun onResponse(call: Call<addPrisoner>, response: Response<addPrisoner>) {
                    println("Add: " + response)
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