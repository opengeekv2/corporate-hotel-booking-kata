package us.plp.corporatehotelbooking

data class HotelDTO(
    val id: Int? = null,
    val name: String? = null,
    val rooms: List<RoomDTO>? = null
)
