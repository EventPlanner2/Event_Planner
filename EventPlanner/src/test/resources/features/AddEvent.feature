Feature: Create incomplete  Event by organizer

  Background: organizer Login
    Given the user is logged in with their account
    And the user is organizer

  Scenario: Successfully Create incomplete Event
    Given the organizer wants to add a new event
    And orginzer created less than 4 events
    When the organizer provides valid event details including:
      |username         |Ali Turabi                       |
      | event ID        | 1                               |
      | Name           | Ali Turabi's Event               |
      | Description    | cars event and explination about many thing about cars in generally                      |
      | Start Date     | 2024-03-15                       |
      | End Date       | 2024-03-15                       |
      | Start Clock    | 19:00                            |
      | End Clock      | 23:00                            |
      | Attendee Count | 50                               |
      | image_path       | C:\Users\USER-M\Downloads\sm20121213_120309-IMG_2953.jpg                       |
    Then the event should be successfully added

  Scenario: Successfully Create second incomplete Event with same organizer
    Given the organizer wants to add another new event
    And orginzer created less than 4 events
    When the organizer provides valid event details including:
      |username         |Ali Turabi                       |
      | event ID        | 2                               |
      | Name           | Ali Turabi's Event   |
      | Description    | cars event and explination about many thing about cars in generally   |
      | Start Date     | 2025-06-20                      |
      | End Date       | 2025-06-20                      |
      | Start Clock    | 8:00                           |
      | End Clock      | 9:00                           |
      | Attendee Count | 100                             |
     | image_path       | C:\Users\USER-M\Downloads\sm20121213_120309-IMG_2953.jpg                           |

    Then the event should be successfully added


  Scenario: Successfully Create third incomplete Event with same organizer
    Given the organizer wants to add another new event
    And orginzer created less than 4 events
    When the organizer provides valid event details including:
      |username         |Ali Turabi                       |
      | event ID        | 3                               |
      | Name           | Ali Turabi's Event   |
      | Description    | cars event and explination about many thing about cars in generally   |
      | Start Date     | 2025-06-20                      |
      | End Date       | 2025-06-20                      |
      | Start Clock    | 10:00                           |
      | End Clock      | 11:00                           |
      | Attendee Count | 100                             |
      | image_path       | C:\Users\USER-M\Downloads\sm20121213_120309-IMG_2953.jpg                           |
    Then the event should be successfully added

  Scenario: UnSuccessfully Create fourth Event  for same organizer
    Given the organizer wants to add the fourth  new event
    When the user already created 3 events
    Then the event should be not added

   # Missing or invalid name ID scenarios
  Scenario: Failed to Create Event with Missing Name ID
    Given the user wants to create a new event
    When the user provides event details with missing name ID
    Then the user should see an error message indicating "missing name ID"

  Scenario: Failed to Create Event with Invalid Name ID
    Given the user wants to create a new event
    When the user provides event details with invalid name ID
    Then the user should see an error message indicating "invalid name ID"




  # Missing or invalid name scenarios
  Scenario: Failed to Create Event with Missing Name
    Given the user wants to create a new event
    When the user provides event details with missing name
    Then the user should see an error message indicating "missing name"

  Scenario: Failed to Create Event with Invalid Name
    Given the user wants to create a new event
    When the user provides event details with invalid name
    Then the user should see an error message indicating "invalid name"



  # Missing or invalid description scenarios
  Scenario: Failed to Create Event with Missing Description
    Given the user wants to create a new event
    When the user provides event details with missing description
    Then the user should see an error message indicating "missing description"

  Scenario: Failed to Create Event with Invalid Description
    Given the user wants to create a new event
    When the user provides event details with invalid description
    Then the user should see an error message indicating "invalid description"

  # Missing or invalid start date scenarios
  Scenario: Failed to Create Event with Missing Start Date
    Given the user wants to create a new event
    When the user provides event details with missing start date
    Then the user should see an error message indicating "missing start date"

  Scenario: Failed to Create Event with Invalid Start Date
    Given the user wants to create a new event
    When the user provides event details with invalid start date
    Then the user should see an error message indicating "invalid start date"

  # Missing or invalid end date scenarios
  Scenario: Failed to Create Event with Missing End Date
    Given the user wants to create a new event
    When the user provides event details with missing end date
    Then the user should see an error message indicating "missing end date"

  Scenario: Failed to Create Event with Invalid End Date
    Given the user wants to create a new event
    When the user provides event details with invalid end date
    Then the user should see an error message indicating "invalid end date"

  # Missing or invalid start clock scenarios
  Scenario: Failed to Create Event with Missing Start Clock
    Given the user wants to create a new event
    When the user provides event details with missing start clock
    Then the user should see an error message indicating "missing start clock"

  Scenario: Failed to Create Event with Invalid Start Clock
    Given the user wants to create a new event
    When the user provides event details with invalid start clock
    Then the user should see an error message indicating "invalid start clock"

  # Missing or invalid end clock scenarios
  Scenario: Failed to Create Event with Missing End Clock
    Given the user wants to create a new event
    When the user provides event details with missing end clock
    Then the user should see an error message indicating "missing end clock"

  Scenario: Failed to Create Event with Invalid End Clock
    Given the user wants to create a new event
    When the user provides event details with invalid end clock
    Then the user should see an error message indicating "invalid end clock"

  # Missing or invalid attendee count scenarios
  Scenario: Failed to Create Event with Missing Attendee Count
    Given the user wants to create a new event
    When the user provides event details with missing attendee count
    Then the user should see an error message indicating "missing attendee count"

  Scenario: Failed to Create Event with Invalid Attendee Count
    Given the user wants to create a new event
    When the user provides event details with invalid attendee count
    Then the user should see an error message indicating "invalid attendee count"


  # Missing or invalid image path scenarios
  Scenario: Failed to Create Event with Missing Image Path
    Given the user wants to create a new event
    When the user provides event details with missing image path
    Then the user should see an error message indicating "missing image path"

  Scenario: Failed to Create Event with Invalid Image Path
    Given the user wants to create a new event
    When the user provides event details with invalid image path
    Then the user should see an error message indicating "invalid image path"
