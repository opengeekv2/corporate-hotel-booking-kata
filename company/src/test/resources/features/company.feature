Feature: Company manager can create and delete employees

  Scenario:
    Given an empty system
    When they create an an employee in company 1 with id 1
    And they create an an employee in company 2 with id 2
    Then they should delete an employee with id 1
    And they should delete an employee with id 2