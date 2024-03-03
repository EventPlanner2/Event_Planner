Feature: Delete Event

  Scenario: User deletes an existing event by ID
    Given there is an event with ID "123"
    When the user deletes the event with ID "123"
    Then the event with ID "123" should be deleted

  Scenario Outline: User tries to delete a non-existing event
    Given there is no event with ID "<nonExistingId>"
    When the user tries to delete the event with ID "<nonExistingId>"
    Then the system should display an error message

    Examples:
      | nonExistingId |
      | 456           |
      | 789           |

  Scenario: User attempts to delete an event with an invalid ID
    Given the user provides an invalid event ID
    When the user tries to delete the event
    Then the system should display an error message

  Scenario Outline: User deletes an event with multiple occurrences
    Given there is an event with ID "<eventID>" and multiple occurrences
    When the user deletes the event with ID "<eventID>"
    Then all occurrences of the event with ID "<eventID>" should be deleted

    Examples:
      | eventID |
      | 101     |
      | 202     |
      | 303     |