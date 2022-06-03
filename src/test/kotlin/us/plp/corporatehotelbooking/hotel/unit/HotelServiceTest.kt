package us.plp.corporatehotelbooking.hotel.unit

import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import us.plp.corporatehotelbooking.hotel.domain.exceptions.HotelAlreadyExists
import us.plp.corporatehotelbooking.hotel.domain.ports.HotelRepository
import us.plp.corporatehotelbooking.hotel.domain.services.HotelService


class HotelServiceTest {

    private val hotelRepository = mockk<HotelRepository>()

    @Test
    fun `should create a new hotel with id 1 and name "test hotel"`() {
        val hotelService = HotelService(hotelRepository)
        hotelService.addHotel(1, "test hotel")
        val hotel = hotelService.findHotelBy(1)
        assertThat(hotel.id).isEqualTo(1)
        assertThat(hotel.name).isEqualTo("test hotel")
    }

    @Test
    fun `should create a new hotel with id 2 and name "second test hotel"`() {
        val hotelService = HotelService(hotelRepository)
        hotelService.addHotel(2, "second test hotel")
        val hotel = hotelService.findHotelBy(2)
        assertThat(hotel.id).isEqualTo(2)
        assertThat(hotel.name).isEqualTo("second test hotel")
    }

    @Test
    fun `should not be able to create an hotel with an already existing id"`() {
        val hotelService = HotelService(hotelRepository)
        hotelService.addHotel(2, "second test hotel")
        assertThatExceptionOfType(HotelAlreadyExists::class.java).isThrownBy {
            hotelService.addHotel(2, "second test hotel")
        }
    }

}
