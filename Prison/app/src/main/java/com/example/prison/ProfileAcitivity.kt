package com.example.prison

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class ProfileAcitivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_acitivity)


        val button_logOut = findViewById<Button>(R.id.button_logOut)

        button_logOut.setOnClickListener {


            val pref = getApplicationContext().getSharedPreferences("my_shared_preff", 0).edit();

            pref.clear()

            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(0,0)
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
        }

        return super.onOptionsItemSelected(item)
    }
}