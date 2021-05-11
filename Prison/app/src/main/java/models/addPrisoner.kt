package models

import retrofit2.http.Field

data class addPrisoner(val name:String,val forname: String,val pesel:String,val adress: String,val pass: Boolean,val behavior: Int,val isolated: Boolean,val idCell:Int)
