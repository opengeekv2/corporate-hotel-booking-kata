package us.plp.corporatehotelbooking.bookingpolicy.domain

import io.cucumber.java.PendingException
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.springframework.beans.factory.annotation.Autowired
import us.plp.corporatehotelbooking.hotel.domain.services.HotelService


class BookingPolicyServiceTest(@Autowired val hotelService: HotelService) {

    @Given("an hotel {int} with a single room")
    fun an_hotel_with_a_single_room(int1: Int) {
        hotelService.addHotel(int1, "Biri biri")
    }

    @Given("an employee with id {int}")
    fun an_employee_with_id(int1: Int?) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @When("employee {int} policy is standard room")
    fun employee_policy_is_standard_room(int1: Int?) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @Then("employee {int} is allowed to book a single room")
    fun employee_is_allowed_to_book_a_single_room(int1: Int?) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }


}
