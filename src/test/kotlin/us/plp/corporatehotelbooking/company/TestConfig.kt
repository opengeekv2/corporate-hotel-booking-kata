package us.plp.corporatehotelbooking.company

import com.ninjasquad.springmockk.MockkBean
import io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE
import io.mockk.mockk
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import us.plp.corporatehotelbooking.company.domain.entities.Employee
import us.plp.corporatehotelbooking.company.domain.ports.EmployeeRepository
import us.plp.corporatehotelbooking.hotel.domain.entities.Hotel
import us.plp.corporatehotelbooking.hotel.domain.ports.HotelRepository

@ComponentScan
@Configuration
class SpringTestConfig {

    @Bean
    fun employeeRepository(): EmployeeRepository {
        return object: EmployeeRepository {

            private val employees = mutableMapOf<Int, Employee>()

            override fun findById(id: Int): Employee? {
                return employees[id]
            }

            override fun add(employee: Employee) {
                employees[employee.id] = employee
            }

            override fun delete(employeeId: Int) {
                employees.remove(employeeId)
            }

        }
    }
}