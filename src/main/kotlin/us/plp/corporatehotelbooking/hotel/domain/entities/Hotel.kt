package us.plp.corporatehotelbooking.hotel.domain.entities

class Hotel(val id: Int, val name: String) {
    val rooms = mutableMapOf<Int, Room>()
}
