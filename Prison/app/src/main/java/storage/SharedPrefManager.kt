package storage

import android.content.Context
import models.LoginResponse


class SharedPrefManager private constructor(private val mCtx: Context) {

    val isLoggedIn: Boolean?
        get() {
            val sharedPreferences = mCtx.getSharedPreferences("SHARED_PREF_NAME", Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean("isLogin",false)
        }

    val user: LoginResponse
        get() {
            val sharedPreferences = mCtx.getSharedPreferences("SHARED_PREF_NAME", Context.MODE_PRIVATE)
            return LoginResponse(
                    sharedPreferences.getString("token",null),
                    sharedPreferences.getString("expiration",null),
                sharedPreferences.getString("roles",null)
            )
        }


    fun saveUser(user: LoginResponse?) {

        val sharedPreferences = mCtx.getSharedPreferences("SHARED_PREF_NAME", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("tokken", user?.token)
        editor.putBoolean("isLogin",true)

        editor.apply()

    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences("SHARED_PREF_NAME", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}