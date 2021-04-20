package com.example.prison

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import storage.SharedPrefManager
import tools.RetrofitClient



class LoginActivity : AppCompatActivity() {


    val userL = "user"
    val userP = "user"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val button = findViewById<Button>(R.id.loginBtn)

        button.setOnClickListener {

//

            val login = findViewById<EditText>(R.id.loginText).toString()
            val password = findViewById<EditText>(R.id.passwordText).toString()

            RetrofitClient.instance.userLogin(login,password).enqueue(object: Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext,"failure",Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<LoginResponse>,response: Response<LoginResponse>) {
                    println(response.body())
//                    SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.user!!)
//                    val intent = Intent(applicationContext,HomeAcitivity::class.java)
//                    startActivity(intent)
                }
            })
        }
    }
}