package tools

import retrofit2.Call
import models.LoginResponse
import models.LogsResponse
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("Authentication/login")
    fun userLogin(
        @Field("UserName") username:String,
        @Field("Password") password:String
    ): Call<LoginResponse>

    @GET("Logger")
    fun getLogs(@Header("Authorization") authorization: String?):Call<List<LogsResponse>>

}