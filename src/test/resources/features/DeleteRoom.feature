Feature: Delete Room

  Background: Admin Login
    Given the system administrator is logged in

  Scenario: Admin deletes an existing Room by ID
    And there is a room with ID "4"
    When the admin deletes the room with ID provided
    Then the room should be deleted


  Scenario: Admin tries to delete a non-existing room
    And there is no room with ID "458"
    When the admin deletes the room with ID provided
    Then the system should display an error message room "Non-Existing Room"


  Scenario: Admin attempts to delete a room with an invalid ID
    And the admin provides an invalid room ID "R11"
    When the admin deletes the room with ID provided
    Then the system should display an error message room "Invalid Room ID"

