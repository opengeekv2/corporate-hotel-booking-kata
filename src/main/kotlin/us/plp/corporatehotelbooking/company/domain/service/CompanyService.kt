package us.plp.corporatehotelbooking.company.domain.service

import org.springframework.stereotype.Service
import us.plp.corporatehotelbooking.company.domain.entities.Employee
import us.plp.corporatehotelbooking.company.domain.exceptions.EmployeeAlreadyExists
import us.plp.corporatehotelbooking.company.domain.ports.EmployeeRepository

@Service
class CompanyService(val employeeRepository: EmployeeRepository) {
    fun addEmployee(companyId: Int, employeeId: Int) {
        if (employeeRepository.findById(employeeId) != null) throw EmployeeAlreadyExists()
        employeeRepository.add(Employee(employeeId, companyId))
    }
}