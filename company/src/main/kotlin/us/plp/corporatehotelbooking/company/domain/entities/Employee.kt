package us.plp.corporatehotelbooking.company.domain.entities

class Employee(val id: Int, val companyId: Int) {

    fun equals(other: Employee): Boolean {
        return id == other.id
    }
}
