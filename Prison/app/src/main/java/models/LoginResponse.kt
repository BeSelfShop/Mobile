package models

data class LoginResponse(val token: String?, val expiration: String?, val roles: String?)