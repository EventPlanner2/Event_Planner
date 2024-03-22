Feature: Delete Room

  Background: Admin Login
    Given the user is logged in with their account
    And the user is admin
  Scenario: User deletes an existing room by ID
    And there is an room with ID "3"
    When the user deletes the room with ID provided
    Then the room should be deleted

  Scenario Outline: User tries to delete a non-existing room
    And there is no room with ID "<nonExistingId>"
    When the user deletes the room with ID provided
    Then the system should display an error message "Non-Existing room"

    Examples:
      | nonExistingId |
      | 456           |
      | 789           |

  Scenario: User attempts to delete an room with an invalid ID
    And the user provides an invalid room ID "abc"
    When the user deletes the room with ID provided
    Then the system should display an error message "Invalid ID"