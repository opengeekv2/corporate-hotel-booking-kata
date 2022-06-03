package us.plp.corporatehotelbooking.hotel

data class HotelValue(
    val id: Int? = null,
    val name: String? = null,
    val rooms: List<RoomValue>? = null
)
