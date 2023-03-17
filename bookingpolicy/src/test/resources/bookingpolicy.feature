Feature: Booking policy manager can manage booking policy

  Scenario:
    Given an hotel 1 with a single room
    And an employee with id 1
    When employee 1 policy is standard room
    Then employee 1 is allowed to book a single room
