Feature: Update Event

  Background: organizer Login
    Given the user is logged in with their account
    And the user is organizer

  Scenario: User Update an existing event by ID
    Given there is an event with ID "2"
    When the user enters an id to update "2"
    And the user enters the new event information
      |username         |Ali Turabi                       |
      | Name           | Ali Turabi's Event Updated             |
      | Description    | cars event and explination about many thing about cars in generally                      |
      | Start Date     | 2024-03-15                       |
      | End Date       | 2024-03-15                       |
      | Start Clock    | 20:00                            |
      | End Clock      | 23:00                            |
      | Attendee Count | 40                               |
      | image_path       |57.png|
    And the user updates the event with ID provided
    Then the event should be updated


  Scenario Outline: User tries to update a non-existing event
    Given there is no event with ID "<nonExistingId>"
    When the user enters an id to update "<nonExistingId>"
    And the user enters the new event information
      |username         |Ali Turabi                       |
      | Name           | Ali Turabi's Event Updated             |
      | Description    | cars event and explination about many thing about cars in generally                      |
      | Start Date     | 2024-03-15                       |
      | End Date       | 2024-03-15                       |
      | Start Clock    | 20:00                            |
      | End Clock      | 23:00                            |
      | Attendee Count | 40                               |
      | image_path       |57.png|
    And the user updates the event with ID provided
    Then the system should display an error message "Non-Existing Event"

    Examples:
      | nonExistingId |
      | 456           |
      | 789           |

  Scenario: User attempts to update an event with an invalid ID
    Given the user provides an invalid event ID "abc"
    When the user enters an id to update "abc"
    And the user updates the event with ID provided
    Then the system should display an error message "Invalid ID"
