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
    fun getLogs(
        @Header("Authorization") authorization: String?)
    :Call<List<LogsResponse>>

    @GET("Prisoner")
    fun getPrisoners(
        @Header("Authorization") authorization: String?
    ):Call<List<PrisonersResponse>>

    @GET("Prisoner/{id}")
    fun getPrisoner(
        @Header("Authorization") authorization: String?,
        @Path("id") id: Int
    ): Call<PrisonerResponse>

    @Headers("Content-Type: application/json")
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
    fun getCells(
        @Header("Authorization") authorization: String?
    ):Call<List<Cell>>


    @Headers("Content-Type: application/json")
    @POST("Prisoner")
    fun addPrisoner(
        @Header("Authorization") authorization: String?,
        @Field("name") name:String,
        @Field("forname") forname:String,
        @Field("pesel") pesel:String,
        @Field("adress") adress:String,
        @Field("pass") pass:Boolean,
        @Field("behavior") behavior:Int,
        @Field("isolated") isolated:Boolean,
        @Field("idCell") idCell: Int
    ):Call<addPrisoner>


    @GET("PCells/{id}")
    fun getCellDetails(
        @Header("Authorization") authorization: String?,
        @Path("id") id: Int
    ):Call<CellDetails>

    @DELETE("Prisoner/{id}")
    fun deletePrisoner(
        @Header("Authorization") authorization: String?,
        @Path("id") id: Int
    ):Call<DeletePrisonerResponse>

    @DELETE("PCells/{id}/")
    fun deleteCell(
        @Header("Authorization") authorization: String?,
        @Path("id") id: Int
    ):Call<DeleteCellResponse>

    @POST("PCells")
    fun addCell(
        @Header("Authorization") authorization: String?,
        @Field("bedsCount") bedsCount: Int,
        @Field("bedsCount") idCellType: Int,
        @Field("cellNumber") cellNumber: String
    ):Call<addCellResponse>

    @PUT("Prisoner/{id}")
    fun updatePrisoner(
        @Header("Authorization") authorization: String?,
        @Path("id") id: Int,
        @Field("name") name:String,
        @Field("forname") forname:String,
        @Field("pesel") pesel:String,
        @Field("address") address:String,
        @Field("idCell") idCell:Int
    ):Call<editPrisonerResponser>


    @PUT("PCells/{id}")
    fun updateCell(
        @Header("Authorization") authorization: String?,
        @Path("id") id: Int,
        @Field("bedsCount") bedsCount:Int,
        @Field("idCellType") idCellType:Int,
        @Field("cellNumber") cellNumber:String
    )

    @DELETE("Isolation/{id}")
    fun deletePrisonerIsolation(
        @Header("Authorization") authorization: String?,
        @Path("id") id: Int
    ):Call<DeletePrisonerResponse>

    @POST("Punishment")
    fun addPunishment(
        @Header("Authorization") authorization: String?,
        @Field("idPrisoner") idPrisoner: Int,
        @Field("idReason") idReason: Int,
        @Field("startDate") startDate: String,
        @Field("endDate") endDate: String
    ):Call<addPunishmentResponse>

    @PUT("Pass/{id}")
    fun editPass(
        @Header("Authorization") authorization: String?,
        @Path("id") id: Int,
        @Field("endDate") endDate: String
    ):Call<editPassResponse>

    @PUT("Punishment/{id}")
    fun editPunishment(
        @Header("Authorization") authorization: String?,
        @Path("id") id: Int,
        @Field("idPrisoner") idPrisoner: Int,
        @Field("idReason") idReason: Int,
        @Field("startDate") startDate: String,
        @Field("endDate") endDate: String
    ):Call<editPunishmentResponse>

    @POST("Isolation/{id}")
    fun addIsolation(
        @Header("Authorization") authorization: String?,
        @Path("id") id: Int,
        @Field("startDate") startDate: String,
        @Field("endDate") endDate: String
    ):Call<addIsolationResponse>

    @POST("Pass/{id}")
    fun addPass(
        @Header("Authorization") authorization: String?,
        @Path("id") id: Int,
        @Field("startDate") startDate: String,
        @Field("endDate") endDate: String
    ):Call<addPassResponse>






}