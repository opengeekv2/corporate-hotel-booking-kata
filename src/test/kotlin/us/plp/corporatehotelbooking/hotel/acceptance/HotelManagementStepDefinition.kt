package us.plp.corporatehotelbooking.hotel.acceptance

import com.ninjasquad.springmockk.MockkBean
import io.cucumber.core.backend.ObjectFactory
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import io.cucumber.spring.CucumberTestContext
import io.cucumber.spring.SpringFactory
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Scope
import us.plp.corporatehotelbooking.hotel.domain.ports.HotelRepository
import us.plp.corporatehotelbooking.hotel.domain.services.HotelService
import us.plp.corporatehotelbooking.hotel.domain.values.HotelValue
import us.plp.corporatehotelbooking.hotel.domain.values.RoomValue


class HotelManagementStepDefinition(
    @Autowired val hotelService: HotelService
    ) {

    @Given("an empty system")
    fun an_empty_system() = Unit

    @When("an hotel manager sets an hotel with id {int} and a single room")
    fun an_hotel_manager_sets_an_hotel_with_id_and_a_single_room(id: Int) {
        // Write code here that turns the phrase above into concrete actions
        hotelService.addHotel(id, "test hotel")
        hotelService.setRoom(id, 1, "single")
    }

    @Then("an hotel manager can get hotel {int} and see a single room")
    fun an_hotel_manager_can_get_hotel_and_see_a_single_room(id: Int) {
        // Write code here that turns the phrase above into concrete actions
        val result = hotelService.findHotelBy(id)
        assertThat(result).isInstanceOf(HotelValue::class.java)
        assertThat(result.id).isEqualTo(1)
        assertThat(result.name).isEqualTo("test hotel")
        assertThat(result.rooms!![0].number).isEqualTo(1)
        assertThat(result.rooms!![0].roomType).isEqualTo(1)
    }

    @When("they set an hotel with id {int} and name {string}")
    fun they_set_an_hotel_with_id_and_name(id: Int, name: String) {
        // Write code here that turns the phrase above into concrete actions
        hotelService.addHotel(id, name)
    }

    @When("add to hotel {int} the room number {int} with type {string}")
    fun add_to_hotel_the_room_number_with_type(id: Int, number: Int, roomType: String) {
        // Write code here that turns the phrase above into concrete actions
        hotelService.setRoom(id, number, roomType)
    }

    @Then("hotel number {int} is named {string}")
    fun hotel_number_is_named(id: Int, name: String) {
        // Write code here that turns the phrase above into concrete actions
        val result = hotelService.findHotelBy(id)
        assertThat(result.name).isEqualTo(name)
    }

    @Then("hotel number {int} has a room {int} of the type {string}")
    fun hotel_number_has_a_room_of_the_type(id: Int, number: Int, roomType: String) {
        // Write code here that turns the phrase above into concrete actions
        val result = hotelService.findHotelBy(id)
        assertThat(result.rooms).containsAnyOf(RoomValue(number, roomType))
    }

}