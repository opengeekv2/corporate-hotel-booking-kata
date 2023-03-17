package us.plp.corporatehotelbooking.bookingpolicy

import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import us.plp.corporatehotelbooking.hotel.domain.services.HotelService
import us.plp.corporatehotelbooking.hotel.infrastructure.HotelInMemoryRepository

@SpringBootConfiguration
@ComponentScan
@Configuration
class SpringTestConfig {

    @Bean
    fun hotelService(): HotelService {
        val hotelRepository = HotelInMemoryRepository()
        return HotelService(hotelRepository)
    }
}