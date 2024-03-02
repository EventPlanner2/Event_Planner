Feature: AddRoom
  As a system administrator,
  I want to add a room (venue/place) to the system

  Background:
    Given the system administrator is logged in

  Scenario: Add a room successfully with all required attributes
    When the administrator adds a room with the following details:
      | Name           | Availability | Capacity | Cost Per Hour | Description                    |
      | Conference Rm  | Available    | 50       | $100          | A spacious conference room     |
    Then the room should be added successfully to the system

  Scenario: Attempt to add a room with a duplicate name
    Given a room with the name "Room 1" already exists
    When the administrator adds a room with the same name
      | Name           | Availability | Capacity | Cost Per Hour | Description                    |
      | Conference Rm  | Available    | 50       | $100          | A spacious conference room     |
    Then the system should display an error indicating that "the room name is already existed"

  Scenario: Attempt to add a room with an invalid role
    Given a Service Provider user is logged in with "FactoryX" and "FactoryX123"
    When the user tries to add a room
      | Name           | Availability | Capacity | Cost Per Hour | Description                    |
      | Conference Rm 2  | Available    | 50       | $100          | A spacious conference room     |
    Then the system should display an error indicating that "only Admin can add rooms"

  Scenario: Attempt to add a room with a negative capacity
    When the administrator adds a room with the following details:
      | Name           | Availability | Capacity | Cost Per Hour | Description                    |
      | Room 2  | Available    | -50       | $100          | A lecture room     |
    Then the system should display an error indicating that "Capacity can't be negative"


  Scenario: Attempt to add a room with an invalid format for capacity
    When the administrator adds a room with the following details:
      | Name           | Availability | Capacity | Cost Per Hour | Description                    |
      | Room 3  | Available    | abs       | $100         | A lecture room     |
    Then the system should display an error indicating that "invalid format for Capacity"

  Scenario: Attempt to add a room with an invalid format for cost
    When the administrator adds a room with the following details:
      | Name           | Availability | Capacity | Cost Per Hour | Description                    |
      | Room 3  | Available    | 50       | *@!100          | A lecture room     |
    Then the system should display an error indicating that "invalid format for Cost Per Hour"

  Scenario: Attempt to add a room with an empty attributes
    When the administrator adds a room with the following details:
      | Name           | Availability | Capacity | Cost Per Hour | Description                    |
      | Room 3  | Available    |       |          | A lecture room     |
    Then the system should display an error indicating that "There is an empty attributes"

  Scenario: Attempt to add a room with huge capacity
    When the administrator adds a room with the following details:
      | Name           | Availability | Capacity | Cost Per Hour | Description                    |
      | Room 3  | Available    |   10000000    |    $100      | A lecture room     |
    Then the system should display an error indicating that "Max Capacity is 1000"




