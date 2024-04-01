Feature: Room Reservation for Incomplete Events

  Background: organizer Login
    Given the user is logged in with their account
    And the user is organizer

  Scenario: Displaying incomplete events and available rooms
    When they choose to Reserve Room
    Then all incomplete events related to the organizer should be displayed
    And all available rooms should be listed

  Scenario: Successfully reserving a room for an incomplete event
    When they choose to Reserve Room
    And the organizer enters EventID "1" and RoomID "1" to reserve
    Then the room should be reserved for the event and the event become complete
    And a confirmation message should be displayed "Room has been reserved with cost 600.0"
    And a notification will be added with all details
    And the Budget of orgainzer should be decreased

  Scenario: Attempting to reserve a room with invalid event ID
    When they choose to Reserve Room
    And the organizer enters an invalid EventID "E999" and a valid RoomID "1"
    Then an error message should be displayed indicating "the event ID is invalid"

  Scenario: Attempting to reserve a room with invalid room ID
    When they choose to Reserve Room
    And the organizer enters a valid EventID "1" and an invalid RoomID "R999"
    Then an error message should be displayed indicating "the room ID is invalid"


  Scenario: Reserving a room that is already reserved for the selected event
    When they choose to Reserve Room
    And the organizer enters EventID "1" and RoomID "1" to reserve
    Then an error message should be displayed indicating "the room is already reserved for the event"

  Scenario: Reserving a room that is already reserved for another event
    When they choose to Reserve Room
    And the organizer enters EventID "2" and RoomID "1" to reserve
    Then an error message should be displayed indicating "the room is already reserved for another event"

  Scenario: Reserving a room that to an event which has a room
    When they choose to Reserve Room
    And the organizer enters EventID "1" and RoomID "2" to reserve
    Then an error message should be displayed indicating "the event is already has a room"

  Scenario: Reserving a room that have lower capacity than attendance count
    When they choose to Reserve Room
    And the organizer enters EventID "2" and RoomID "2" to reserve
    Then an error message should be displayed indicating "the room is lower capacity than attendance count"
  Scenario: Reserving a room with low amount of budget
    When they choose to Reserve Room
    And the budget of the orgainzer is lower than the cost
    And the organizer enters a valid "4" for event and "1000" for room
    Then an error message should be displayed indicating "Low Budget"




