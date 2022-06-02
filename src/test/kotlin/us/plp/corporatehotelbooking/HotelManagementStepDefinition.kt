package us.plp.corporatehotelbooking

import io.cucumber.java.PendingException
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired

class HotelManagementStepDefinition(
    @Autowired val hotelService: HotelService
) {

    @Given("an empty system")
    fun an_empty_system() {

    }

    @When("an hotel manager sets an hotel with id {int} and a single room")
    fun an_hotel_manager_sets_an_hotel_with_id_and_a_single_room(id: Int?) {
        // Write code here that turns the phrase above into concrete actions
        hotelService.addHotel(id, "test hotel")
        hotelService.setRoom(id, 1, "single")
    }

    @Then("an hotel manager can get hotel {int} and see a single room")
    fun an_hotel_manager_can_get_hotel_and_see_a_single_room(id: Int?) {
        // Write code here that turns the phrase above into concrete actions
        val result = hotelService.findHotelBy(id)
        assertThat(result).isInstanceOf(HotelDTO::class.java)
    }

}