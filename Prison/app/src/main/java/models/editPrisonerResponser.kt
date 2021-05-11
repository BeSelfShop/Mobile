package models

import retrofit2.http.Field

data class editPrisonerResponser(val id: Int, val name:String, val forname:String, val pesel:String, val address:String, val idCell:Int)