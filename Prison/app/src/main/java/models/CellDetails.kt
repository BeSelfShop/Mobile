package models

data class CellDetails(
    val bedsCount: Int,
    val cellNumber: String,
    val id: Int,
    val idCellType: Int,
    val idPrison: Int,
    val occupiedBeds: Int
)