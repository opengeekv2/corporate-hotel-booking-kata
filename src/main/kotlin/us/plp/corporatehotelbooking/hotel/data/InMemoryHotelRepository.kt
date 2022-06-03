package us.plp.corporatehotelbooking.hotel.data

import org.springframework.stereotype.Repository
import us.plp.corporatehotelbooking.hotel.domain.entities.Hotel
import us.plp.corporatehotelbooking.hotel.domain.ports.HotelRepository

@Repository
class InMemoryHotelRepository: HotelRepository {

    private val hotels = mutableMapOf<Int, Hotel>()

    override fun findById(id: Int): Hotel? {
        return hotels[id]
    }

    override fun add(hotel: Hotel) {
        hotels[hotel.id] = hotel
    }
}