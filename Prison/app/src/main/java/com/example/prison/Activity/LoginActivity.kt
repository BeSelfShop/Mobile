package com.example.prison.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.prison.R
import models.LoginReq
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import storage.SharedPrefManager
import tools.Api
import tools.RetrofitClient



class LoginActivity : AppCompatActivity() {


    val userL = "user"
    val userP = "user"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val buttonLogin = findViewById<Button>(R.id.loginBtn)
        val buttonRegister = findViewById<Button>(R.id.butRegister)

        val pref = getApplicationContext().getSharedPreferences("my_shared_preff", 0)
        println("1.LoginAcitivity")
        pref.edit().clear()
        println(pref.getString("token",""))

        buttonLogin.setOnClickListener {


            val login = findViewById<EditText>(R.id.loginText).text.toString()
            val password = findViewById<EditText>(R.id.passwordText).text.toString()


            println(login + " " + password)

            RetrofitClient.buildApi(Api::class.java).userLogin(login,password).enqueue(object: Callback<LoginReq>{
                override fun onFailure(call: Call<LoginReq>, t: Throwable) {
                    Toast.makeText(this@LoginActivity,"nie dziala LoginActivity",Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<LoginReq>, req: Response<LoginReq>) {

                    println(req.body())

                    if(req.body() != null)
                    {
                        SharedPrefManager.getInstance(applicationContext).saveUser(LoginReq(req.body()?.token,req.body()?.expiration,req.body()?.roles))
                        val intent = Intent(applicationContext, HomeAcitivity::class.java)
                        startActivity(intent)
                    }

                }
            })

        }
        buttonRegister.setOnClickListener{
            val intent = Intent(applicationContext, RegisterAcitivity::class.java)
            startActivity(intent)
        }



    }
}