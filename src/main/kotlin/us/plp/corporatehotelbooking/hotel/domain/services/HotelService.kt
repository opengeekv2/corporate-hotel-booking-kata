package us.plp.corporatehotelbooking.hotel.domain.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import us.plp.corporatehotelbooking.hotel.domain.entities.Hotel
import us.plp.corporatehotelbooking.hotel.domain.values.HotelValue
import us.plp.corporatehotelbooking.hotel.domain.exceptions.HotelAlreadyExists
import us.plp.corporatehotelbooking.hotel.domain.ports.HotelRepository


private fun hotelValueFactory(hotel: Hotel): HotelValue {
    return HotelValue(
            hotel.id,
            hotel.name
    )
}

@Service
class HotelService(
        @Autowired val hotelRepository: HotelRepository
) {

    private val hotels = mutableMapOf<Int, Hotel>()

    fun addHotel(id: Int, name: String) {
        val hotel = hotelRepository.findById(id)
        if (hotel != null) throw HotelAlreadyExists()
        hotelRepository.add(Hotel(id, name))
    }

    fun setRoom(id: Int?, i: Int, s: String) {
        TODO()
    }

    fun findHotelBy(id: Int): HotelValue {
        val hotel = hotelRepository.findById(id)
        hotel ?: return HotelValue()
        return hotelValueFactory(hotel)
    }
}