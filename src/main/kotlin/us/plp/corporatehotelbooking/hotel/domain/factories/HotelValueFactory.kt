package us.plp.corporatehotelbooking.hotel.domain.factories

import us.plp.corporatehotelbooking.hotel.domain.entities.Hotel
import us.plp.corporatehotelbooking.hotel.domain.values.HotelValue

typealias HotelValueFactory = (Hotel) -> HotelValue

fun hotelValueFactory(): HotelValueFactory {
    return fun (hotel: Hotel): HotelValue {
        return HotelValue(
            hotel.id,
            hotel.name
        )
    }
}
