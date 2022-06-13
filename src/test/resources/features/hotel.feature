Feature: Hotel manager can manage hotels and rooms

  Scenario:
    Given an empty system
    When they set an hotel with id 1 and name "first hotel"
    And add to hotel 1 the room number 1 with type "single room"
    And they set an hotel with id 2 and name "second hotel"
    And add to hotel 2 the room number 1 with type "double room"
    And add to hotel 2 the room number 2 with type "suite"
    Then hotel number 1 is named "first hotel"
    And hotel number 1 has a room 1 of the type "single room"
    Then hotel number 2 is named "second hotel"
    And hotel number 2 has a room 1 of the type "double room"
    And hotel number 2 has a room 2 of the type "suite"