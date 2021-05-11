package com.example.prison.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.prison.R
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tools.Api

class EditCellActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_cell)


        val button = findViewById<Button>(R.id.editCellBtn)

        val cell_id = getIntent().getStringExtra("id").toString().toInt()
//        val cell_id = intent

        println("intent: " + cell_id)


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

        button.setOnClickListener {
            val beds = findViewById<TextView>(R.id.editCellBedsCount).text.toString().toInt()
            val idType = findViewById<TextView>(R.id.editCellIdCellType).text.toString().toInt()
            val number = findViewById<TextView>(R.id.editCellIdNumber).text.toString()

            retrofit.updateCell("Bearer "+token,cell_id,beds,idType,number)

            startActivity(Intent(this,CellDetailsActivity::class.java).putExtra("cell_id",cell_id))
        }

    }
}