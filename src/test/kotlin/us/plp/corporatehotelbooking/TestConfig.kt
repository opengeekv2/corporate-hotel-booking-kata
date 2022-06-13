package us.plp.corporatehotelbooking

import com.ninjasquad.springmockk.MockkBean
import io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE
import io.mockk.mockk
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import us.plp.corporatehotelbooking.hotel.domain.entities.Hotel
import us.plp.corporatehotelbooking.hotel.domain.ports.HotelRepository

@ComponentScan
@Configuration
class SpringTestConfig {
    @Bean
    fun hotelRepository(): HotelRepository {
        return object: HotelRepository {

            private val hotels = mutableMapOf<Int, Hotel>()

            override fun findById(id: Int): Hotel? {
                return hotels[id]
            }

            override fun add(hotel: Hotel) {
                TODO("Not yet implemented")
            }

            override fun save(hotel: Hotel) {
                TODO("Not yet implemented")
            }

        }
    }
}