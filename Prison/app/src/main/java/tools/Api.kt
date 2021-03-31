package tools

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import models.LoginResponse

interface Api {

    @FormUrlEncoded
    @POST("Authentication/login")
    fun userLogin(
        @Field("username") username:String,
        @Field("password") password:String
    ): Call<LoginResponse>

}