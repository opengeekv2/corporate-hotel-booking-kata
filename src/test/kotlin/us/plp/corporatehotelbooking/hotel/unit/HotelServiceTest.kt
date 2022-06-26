package us.plp.corporatehotelbooking.hotel.unit

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import us.plp.corporatehotelbooking.hotel.domain.entities.Hotel
import us.plp.corporatehotelbooking.hotel.domain.entities.Room
import us.plp.corporatehotelbooking.hotel.domain.exceptions.HotelAlreadyExists
import us.plp.corporatehotelbooking.hotel.domain.exceptions.HotelDoesNotExist
import us.plp.corporatehotelbooking.hotel.domain.ports.HotelRepository
import us.plp.corporatehotelbooking.hotel.domain.services.HotelService
import us.plp.corporatehotelbooking.hotel.domain.values.RoomValue


class HotelServiceTest {

    private val hotelRepository = mockk<HotelRepository>(relaxed = true)

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

        verify(exactly = 0) { hotelRepository.add(any()) }
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

    @Test
    fun `should create a new room with id 1 in hotel 1 with room type "suite"`() {
        val hotelService = HotelService(hotelRepository)

        val hotel = Hotel(1, "first hotel")

        val hotelSlot = slot<Hotel>()

        every { hotelRepository.findById(hotel.id) } returns hotel
        every { hotelRepository.save(capture(hotelSlot)) } returns Unit

        hotelService.setRoom(hotel.id, 1, "suite")

        assertThat(hotelSlot.captured.id).isEqualTo(hotel.id)
        assertThat(hotelSlot.captured.name).isEqualTo(hotel.name)
        assertThat(hotelSlot.captured.rooms[1]!!.number).isEqualTo(1)
        assertThat(hotelSlot.captured.rooms[1]!!.roomType).isEqualTo("suite")
    }

    @Test
    fun `should create a new room with number 2 in hotel 2 with room type "double room"`() {
        val hotelService = HotelService(hotelRepository)

        val hotel = Hotel(2, "second hotel")

        val hotelSlot = slot<Hotel>()

        every { hotelRepository.findById(hotel.id) } returns hotel
        every { hotelRepository.save(capture(hotelSlot)) } returns Unit

        hotelService.setRoom(hotel.id, 2, "double room")

        assertThat(hotelSlot.captured.id).isEqualTo(hotel.id)
        assertThat(hotelSlot.captured.name).isEqualTo(hotel.name)
        assertThat(hotelSlot.captured.rooms[2]!!.number).isEqualTo(2)
        assertThat(hotelSlot.captured.rooms[2]!!.roomType).isEqualTo("double room")
    }

    @Test
    fun `should throw an exception if the hotel does not exist`() {
        val hotelService = HotelService(hotelRepository)

        val hotel = Hotel(1, "first hotel")

        every { hotelRepository.findById(hotel.id) } returns null

        assertThatExceptionOfType(HotelDoesNotExist::class.java).isThrownBy {
            hotelService.setRoom(hotel.id, 1, "single")
        }

        verify(exactly = 0) { hotelRepository.save(any()) }
    }

    @Test
    fun `should return all the information about the rooms of the hotel with specified id - no rooms`() {
        val hotelService = HotelService(hotelRepository)

        val hotel = Hotel(1, "first hotel")

        every { hotelRepository.findById(hotel.id) } returns hotel

        val result = hotelService.findHotelBy(1)
        assertThat(result.numberOfRooms()).isEqualTo(0)
        assertThat(result.rooms).isEqualTo(listOf<RoomValue>())
    }

    @Test
    fun `should return all the information about the rooms of the hotel with specified id - a room`() {
        val hotelService = HotelService(hotelRepository)

        val hotel = Hotel(1, "first hotel")
        hotel.rooms[1] = Room(1, "single")

        every { hotelRepository.findById(hotel.id) } returns hotel

        val result = hotelService.findHotelBy(1)
        assertThat(result.numberOfRooms()).isEqualTo(1)
        assertThat(result.rooms).isEqualTo(listOf(RoomValue(1, "single")))
    }

    @Test
    fun `should return all the information about the rooms of the hotel with specified id - many rooms`() {
        val hotelService = HotelService(hotelRepository)

        val hotel = Hotel(1, "first hotel")
        hotel.rooms[1] = Room(1, "single")
        hotel.rooms[2] = Room(2, "double")

        every { hotelRepository.findById(hotel.id) } returns hotel

        val result = hotelService.findHotelBy(1)
        assertThat(result.numberOfRooms()).isEqualTo(2)
        assertThat(result.rooms).isEqualTo(listOf(
            RoomValue(1, "single"),
            RoomValue(2, "double")
        ))
    }
}
