package us.plp.corporatehotelbooking.hotel

import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import us.plp.corporatehotelbooking.hotel.domain.entities.Hotel
import us.plp.corporatehotelbooking.hotel.domain.ports.HotelRepository

@SpringBootConfiguration
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
                hotels[hotel.id] = hotel
            }

            override fun save(hotel: Hotel) {
                hotels[hotel.id] = hotel
            }

        }
    }
}