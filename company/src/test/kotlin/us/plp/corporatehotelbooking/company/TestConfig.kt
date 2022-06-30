package us.plp.corporatehotelbooking.company

import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import us.plp.corporatehotelbooking.company.domain.entities.Employee
import us.plp.corporatehotelbooking.company.domain.ports.EmployeeRepository

@SpringBootConfiguration
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