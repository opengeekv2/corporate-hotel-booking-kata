package us.plp.corporatehotelbooking.company.domain

import io.cucumber.java.PendingException
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import us.plp.corporatehotelbooking.company.domain.service.CompanyService

class CompanyManagementStepDefinition(
    val companyService: CompanyService
) {
    @When("they create an an employee in company {int} with id {int}")
    fun they_create_an_an_employee_in_company_with_id(companyId: Int, employeeId: Int) {
        companyService.addEmployee(companyId, employeeId)
    }

    @Then("they should delete an employee with id {int}")
    fun they_should_delete_an_employee_with_id(employeeId: Int) {
        companyService.removeEmployee(employeeId)
    }
}