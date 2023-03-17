package us.plp.corporatehotelbooking.hotel.infrastructure

import us.plp.corporatehotelbooking.hotel.domain.entities.Hotel
import us.plp.corporatehotelbooking.hotel.domain.ports.HotelRepository

class HotelInMemoryRepository: HotelRepository {

    private val hotels = mutableMapOf<Int, Hotel>()

    override fun findById(id: Int): Hotel? {
        return hotels[id]
    }

    override fun add(hotel: Hotel) {
        hotels[hotel.id] = hotel
    }

    override fun save(hotel: Hotel) {
        hotels[hotel.id] = hotel
    }

}