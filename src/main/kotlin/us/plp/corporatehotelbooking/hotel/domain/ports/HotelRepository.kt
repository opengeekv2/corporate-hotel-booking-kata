package us.plp.corporatehotelbooking.hotel.domain.ports

import us.plp.corporatehotelbooking.hotel.domain.entities.Hotel

interface HotelRepository {
    fun findById(id: Int): Hotel?
    fun add(hotel: Hotel)
}