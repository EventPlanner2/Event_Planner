Feature: Create incomplete  Event by organizer

  Background: organizer Login
    Given the user is logged in with their account
    And the user is organizer

  Scenario: Successfully Create incomplete Event
    Given the organizer wants to add a new event
    And orginzer created less than 4 events
    When the organizer provides valid event details including:
      |username         |Ali Turabi                       |
      | event ID        | 4                               |
      | Name           | Ali Turabi's Event               |
      | Description    | cars event and explination about many thing about cars in generally                      |
      | Start Date     | 2024-03-15                       |
      | End Date       | 2024-03-15                       |
      | Start Clock    | 19:00                            |
      | End Clock      | 23:00                            |
      | Attendee Count | 50                               |
      | image_path       |57.png|
    Then the event should be successfully added
    And a notification will be added with all details

  Scenario: Successfully Create second incomplete Event with same organizer
    Given the organizer wants to add another new event
    And orginzer created less than 4 events
    When the organizer provides valid event details including:
      |username        |Ali Turabi                       |
      | event ID       | 5                               |
      | Name           | Ali Turabi's Event   |
      | Description    | cars event and explination about many thing about cars in generally   |
      | Start Date     | 2025-06-20                      |
      | End Date       | 2025-06-20                      |
      | Start Clock    | 08:00                            |
      | End Clock      | 09:00                            |
      | Attendee Count | 100                             |
     | image_path       |57.png|

    Then the event should be successfully added
    And a notification will be added with all details


  Scenario: Successfully Create third incomplete Event with same organizer
    Given the organizer wants to add another new event
    And orginzer created less than 4 events
    When the organizer provides valid event details including:
      |username         |Ali Turabi                       |
      | event ID        | 6                               |
      | Name           | Ali Turabi's Event   |
      | Description    | cars event and explination about many thing about cars in generally   |
      | Start Date     | 2025-06-20                      |
      | End Date       | 2025-06-20                      |
      | Start Clock    | 10:00                           |
      | End Clock      | 11:00                           |
      | Attendee Count | 100                             |
      | image_path       |57.png|
    Then the event should be successfully added
    And a notification will be added with all details

  Scenario: UnSuccessfully Create fourth Event  for same organizer
    Given the organizer wants to add the fourth  new event
    When the user already created 3 events
    And the user choose to add event
    Then the event should be not added


  # Missing or invalid name scenarios
  Scenario: Failed to Create Event with Missing Name
    Given the user wants to create a new event
    When the user provides event details with missing name
    And the user choose to add event
    Then the user should see an error message indicating "missing name"

  Scenario: Failed to Create Event with Invalid Name
    Given the user wants to create a new event
    When the user provides event details with invalid name
    And the user choose to add event
    Then the user should see an error message indicating "invalid name"



  # Missing or invalid description scenarios
  Scenario: Failed to Create Event with Missing Description
    Given the user wants to create a new event
    When the user provides event details with missing description
    And the user choose to add event
    Then the user should see an error message indicating "missing description"

  Scenario: Failed to Create Event with Invalid Description
    Given the user wants to create a new event
    When the user provides event details with invalid description
    And the user choose to add event
    Then the user should see an error message indicating "invalid description"

  # Missing or invalid start date scenarios
  Scenario: Failed to Create Event with Missing Start Date
    Given the user wants to create a new event
    When the user provides event details with missing start date
    And the user choose to add event
    Then the user should see an error message indicating "missing start date"

  Scenario: Failed to Create Event with Invalid Start Date
    Given the user wants to create a new event
    When the user provides event details with invalid start date
    And the user choose to add event
    Then the user should see an error message indicating "invalid start date"

  # Missing or invalid end date scenarios
  Scenario: Failed to Create Event with Missing End Date
    Given the user wants to create a new event
    When the user provides event details with missing end date
    And the user choose to add event
    Then the user should see an error message indicating "missing end date"

  Scenario: Failed to Create Event with Invalid End Date
    Given the user wants to create a new event
    When the user provides event details with invalid end date
    And the user choose to add event
    Then the user should see an error message indicating "invalid end date"

  # Missing or invalid start clock scenarios
  Scenario: Failed to Create Event with Missing Start Clock
    Given the user wants to create a new event
    When the user provides event details with missing start clock
    And the user choose to add event
    Then the user should see an error message indicating "missing start clock"

  Scenario: Failed to Create Event with Invalid Start Clock
    Given the user wants to create a new event
    When the user provides event details with invalid start clock
    And the user choose to add event
    Then the user should see an error message indicating "invalid start clock"

  # Missing or invalid end clock scenarios
  Scenario: Failed to Create Event with Missing End Clock
    Given the user wants to create a new event
    When the user provides event details with missing end clock
    And the user choose to add event
    Then the user should see an error message indicating "missing end clock"

  Scenario: Failed to Create Event with Invalid End Clock
    Given the user wants to create a new event
    When the user provides event details with invalid end clock
    And the user choose to add event
    Then the user should see an error message indicating "invalid end clock"

  # Missing or invalid attendee count scenarios
  Scenario: Failed to Create Event with Missing Attendee Count
    Given the user wants to create a new event
    When the user provides event details with missing attendee count
    And the user choose to add event
    Then the user should see an error message indicating "missing attendee count"

  Scenario: Failed to Create Event with Invalid Attendee Count
    Given the user wants to create a new event
    When the user provides event details with invalid attendee count
    And the user choose to add event
    Then the user should see an error message indicating "invalid attendee count"


  # Missing or invalid image path scenarios
  Scenario: Failed to Create Event with Missing Image Path
    Given the user wants to create a new event
    When the user provides event details with missing image path
    And the user choose to add event
    Then the user should see an error message indicating "missing image path"

  Scenario: Failed to Create Event with Invalid Image Path
    Given the user wants to create a new event
    When the user provides event details with invalid image path
    And the user choose to add event
    Then the user should see an error message indicating "invalid image path"
