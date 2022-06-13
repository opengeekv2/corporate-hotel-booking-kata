package us.plp.corporatehotelbooking.hotel.domain.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import us.plp.corporatehotelbooking.hotel.domain.entities.Hotel
import us.plp.corporatehotelbooking.hotel.domain.entities.Room
import us.plp.corporatehotelbooking.hotel.domain.values.HotelValue
import us.plp.corporatehotelbooking.hotel.domain.exceptions.HotelAlreadyExists
import us.plp.corporatehotelbooking.hotel.domain.exceptions.HotelDoesNotExist
import us.plp.corporatehotelbooking.hotel.domain.ports.HotelRepository
import us.plp.corporatehotelbooking.hotel.domain.values.RoomValue


private fun hotelValueFactory(hotel: Hotel): HotelValue {
    return HotelValue(
            hotel.id,
            hotel.name,
            hotel.rooms.map { RoomValue(it.value.number, it.value.roomType) }
    )
}

@Service
class HotelService(
        @Autowired(required=false) val hotelRepository: HotelRepository
) {

    private val hotels = mutableMapOf<Int, Hotel>()

    fun addHotel(id: Int, name: String) {
        val hotel = hotelRepository.findById(id)
        if (hotel != null) throw HotelAlreadyExists()
        hotelRepository.add(Hotel(id, name))
    }

    fun setRoom(hotelId: Int, number: Int, roomType: String) {
        val hotel = hotelRepository.findById(hotelId) ?: throw HotelDoesNotExist()
        hotel.rooms[number] = Room(1, "single")
        hotelRepository.save(hotel)
    }

    fun findHotelBy(id: Int): HotelValue {
        val hotel = hotelRepository.findById(id)
        hotel ?: return HotelValue()
        return hotelValueFactory(hotel)
    }
}