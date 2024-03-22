Feature: Room Reservation for Incomplete Events

  Background: organizer Login
    Given the user is logged in with their account
    And the user is organizer

  Scenario: Displaying incomplete events and available rooms
    When they choose to Reserve Room
    Then all incomplete events related to the organizer should be displayed
    And all available rooms should be listed

  Scenario: Successfully reserving a room for an incomplete event
    When the organizer enters EventID "1" and RoomID "1" to reserve
    Then the room should be reserved for the event and the event become complete
    And a confirmation message should be displayed "Room has been reserved"
    And a notification will be added with all details

  Scenario: Attempting to reserve a room with invalid event ID
    When the organizer enters an invalid EventID "E999" and a valid RoomID "1"
    Then an error message should be displayed indicating "the event ID is invalid"

  Scenario: Attempting to reserve a room with invalid room ID
    When the organizer enters a valid EventID "1" and an invalid RoomID "R999"
    Then an error message should be displayed indicating "the room ID is invalid"


  Scenario: Reserving a room that is already reserved for the selected event
    When the organizer enters EventID "1" and RoomID "1" to reserve
    Then an error message should be displayed indicating "the room is already reserved for the event"

  Scenario: Reserving a room that is already reserved for another event
    When the organizer enters EventID "2" and RoomID "1" to reserve
    Then an error message should be displayed indicating "the room is already reserved for another event"

  Scenario: Reserving a room that to an event which has a room
    When the organizer enters EventID "1" and RoomID "2" to reserve
    Then an error message should be displayed indicating "the event is already has a room"

  Scenario: Reserving a room that have lower capacity than attendance count
    When the organizer enters EventID "2" and RoomID "2" to reserve
    Then an error message should be displayed indicating "the room is lower capacity than attendance count"




