package tools

import models.*
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("Authentication/login")
    fun userLogin(
        @Field("UserName") username:String,
        @Field("Password") password:String
    ): Call<LoginReq>


    @GET("Logger")
    fun getLogs(@Header("Authorization") authorization: String?):Call<List<LogsResponse>>

    @GET("Prisoner")
    fun getPrisoners(@Header("Authorization") authorization: String?):Call<List<PrisonersResponse>>

    @GET("Prisoner/{id}")
    fun getPrisoner(@Header("Authorization") authorization: String?, @Path("id") id: Int): Call<PrisonerResponse>

    @FormUrlEncoded
    @POST("/Authentication/register")
    fun userRegister(
        @Field("userName") username:String,
        @Field("email") email:String,
        @Field("password") password:String,
        @Field("name") name:String,
        @Field("forname") forname:String,
        @Field("inviteCode") inviteCode:String
    ):Call<RegisterReq>

    @GET("PCells")
    fun getCells(@Header("Authorization") authorization: String?):Call<List<Cell>>


    @FormUrlEncoded
    @POST("Prisoner")
    fun addPrisoner(
        @Header("Authorization") authorization: String?,
        @Field("name") name:String,
        @Field("forname") forname:String,
        @Field("pesel") pesel:String,
        @Field("adress") adress:String
    ):Call<addPrisoner>


    @GET("PCells/{id}")
    fun getCellDetails(@Header("Authorization") authorization: String?, @Path("id") id: Int):Call<CellDetails>





}