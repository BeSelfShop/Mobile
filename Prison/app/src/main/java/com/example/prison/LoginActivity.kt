package com.example.prison

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {


    val userL = "user"
    val userP = "user"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.loginBtn)

        button.setOnClickListener {

            val intent = Intent(this,HomeAcitivity::class.java)

            val login = findViewById<EditText>(R.id.loginText)
            val password = findViewById<EditText>(R.id.passwordText)

            if(userL == login.text.toString())
            {
                if(userL == password.text.toString())
                {
                    startActivity(intent)
                }
            }
        }
    }
}