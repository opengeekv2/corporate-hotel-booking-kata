package us.plp.corporatehotelbooking.hotel

import us.plp.corporatehotelbooking.hotel.infrastructure.HotelInMemoryRepository
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import us.plp.corporatehotelbooking.hotel.domain.ports.HotelRepository

@SpringBootConfiguration
@ComponentScan
@Configuration
class SpringTestConfig {
    @Bean
    fun hotelRepository(): HotelRepository {
        return HotelInMemoryRepository()
    }
}