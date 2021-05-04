package models

data class RegisterReq(
    val userName: String,
    val email: String,
    val password: String,
    val name: String,
    val forname: String,
    val inviteCode: String
)