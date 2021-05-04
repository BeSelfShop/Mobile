package models

data class LoginReq(val token: String?, val expiration: String?, val roles: String?)