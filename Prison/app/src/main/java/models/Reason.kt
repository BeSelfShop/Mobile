package models

data class Reason(
    val id: Int,
    val punishments: List<Any>,
    val reasonName: String
)