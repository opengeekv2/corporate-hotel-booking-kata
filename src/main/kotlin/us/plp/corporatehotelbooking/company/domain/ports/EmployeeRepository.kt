package us.plp.corporatehotelbooking.company.domain.ports

import us.plp.corporatehotelbooking.company.domain.entities.Employee

interface EmployeeRepository {
    fun add(employee: Employee)
    fun findById(employeeId: Int): Employee?
}
