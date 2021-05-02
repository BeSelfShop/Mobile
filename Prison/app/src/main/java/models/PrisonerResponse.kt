package models

data class PrisonerResponse(
    val address: String,
    val behavior: Int,
    val forname: String,
    val id: Int,
    val idCell: Int,
    val isolated: Boolean,
    val name: String,
    val pass: Boolean,
    val pesel: String
)