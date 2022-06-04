package us.plp.corporatehotelbooking.hotel.unit

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import us.plp.corporatehotelbooking.hotel.domain.entities.Hotel
import us.plp.corporatehotelbooking.hotel.domain.exceptions.HotelAlreadyExists
import us.plp.corporatehotelbooking.hotel.domain.ports.HotelRepository
import us.plp.corporatehotelbooking.hotel.domain.services.HotelService


class HotelServiceTest {

    private val hotelRepository = mockk<HotelRepository>()

    @ParameterizedTest
    @CsvSource(
        "1, test hotel",
        "2, second test hotel"
    )
    fun `should create a new hotel with id 1 and name "test hotel"`(id: Int, name: String) {
        val hotelService = HotelService(hotelRepository)

        val hotelSlot = slot<Hotel>()

        every { hotelRepository.findById(id) } returns null
        every { hotelRepository.add(capture(hotelSlot)) } returns Unit

        hotelService.addHotel(id, name)

        assertThat(hotelSlot.captured.id).isEqualTo(id)
        assertThat(hotelSlot.captured.name).isEqualTo(name)
    }

    @Test
    fun `should not be able to create an hotel with an already existing id"`() {
        val hotelService = HotelService(hotelRepository)

        every { hotelRepository.findById(2) } returns Hotel(2, "second hotel test")

        assertThatExceptionOfType(HotelAlreadyExists::class.java).isThrownBy {
            hotelService.addHotel(2, "second test hotel")
        }
    }

    @Test
    fun `should create a new room with id 1 in hotel 1 with room type "single"`() {
        val hotelService = HotelService(hotelRepository)

        val hotel = Hotel(1, "first hotel")

        val hotelSlot = slot<Hotel>()

        every { hotelRepository.findById(hotel.id) } returns hotel
        every { hotelRepository.save(capture(hotelSlot)) } returns Unit

        hotelService.setRoom(hotel.id, 1, "single")

        assertThat(hotelSlot.captured.id).isEqualTo(hotel.id)
        assertThat(hotelSlot.captured.name).isEqualTo(hotel.name)
        assertThat(hotelSlot.captured.rooms[1]!!.number).isEqualTo(1)
        assertThat(hotelSlot.captured.rooms[1]!!.roomType).isEqualTo("single")
    }

}
