package models

data class Punishment(
    val endDate: String,
    val id: Int,
    val idPrisoner: Int,
    val idReason: Int,
    val lifery: Boolean,
    val reason: Reason,
    val startDate: String
)