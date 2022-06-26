package us.plp.corporatehotelbooking.company.domain

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import us.plp.corporatehotelbooking.company.domain.entities.Employee
import us.plp.corporatehotelbooking.company.domain.ports.EmployeeRepository
import us.plp.corporatehotelbooking.company.domain.service.CompanyService
import us.plp.corporatehotelbooking.company.domain.exceptions.EmployeeAlreadyExists
import us.plp.corporatehotelbooking.hotel.domain.exceptions.HotelAlreadyExists


class CompanyServiceTest {

    private val employeeRepository = mockk<EmployeeRepository>(relaxed = true)

    @ParameterizedTest
    @CsvSource("1, 1", "2, 2")
    fun `should create an employee with id 1 in company with id 1`(companyId: Int, employeeId: Int) {
        val companyService = CompanyService(employeeRepository)

        every { employeeRepository.findById(employeeId) } returns null

        val employeeSlot = slot<Employee>()

        every { employeeRepository.add(capture(employeeSlot)) } returns Unit

        companyService.addEmployee(companyId, employeeId)

        assertThat(employeeSlot.captured.id).isEqualTo(employeeId)
        assertThat(employeeSlot.captured.companyId).isEqualTo(companyId)
    }

    @Test
    fun `employees should not be duplicated`() {
        val companyService = CompanyService(employeeRepository)

        every { employeeRepository.findById(1) } returns Employee(1, 1)

        Assertions.assertThatExceptionOfType(EmployeeAlreadyExists::class.java).isThrownBy {
            companyService.addEmployee(1, 1)
        }

        verify(exactly = 0) { employeeRepository.add(any()) }
    }
}
