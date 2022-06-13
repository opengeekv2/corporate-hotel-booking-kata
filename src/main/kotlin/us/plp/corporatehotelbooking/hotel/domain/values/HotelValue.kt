package us.plp.corporatehotelbooking.hotel.domain.values

data class HotelValue(
    val id: Int? = null,
    val name: String? = null,
    val rooms: List<RoomValue> = listOf()
) {
    fun numberOfRooms(): Int {
        return 0
    }
}
