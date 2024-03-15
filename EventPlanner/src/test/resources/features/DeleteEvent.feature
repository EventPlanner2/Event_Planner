Feature: Delete Event

  Background: organizer Login
    Given the user is logged in with their account
    And the user is organizer
  Scenario: User deletes an existing event by ID
    And there is an event with ID "1"
    When the user deletes the event with ID provided
    Then the event should be deleted

  Scenario Outline: User tries to delete a non-existing event
    And there is no event with ID "<nonExistingId>"
    When the user deletes the event with ID provided
    Then the system should display an error message "Non-Existing Event"

    Examples:
      | nonExistingId |
      | 456           |
      | 789           |

  Scenario: User attempts to delete an event with an invalid ID
    And the user provides an invalid event ID "abc"
    When the user deletes the event with ID provided
    Then the system should display an error message "Invalid ID"
