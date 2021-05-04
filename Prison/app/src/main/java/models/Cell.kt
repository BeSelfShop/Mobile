package models

data class Cell(
    val bedsCount: Int,
    val cellNumber: String,
    val cellType: CellType,
    val id: Int,
    val idCellType: Int,
    val idPrison: Int,
    val occupiedBeds: Int,
    val prison: Any,
    val prisoner: Any
)