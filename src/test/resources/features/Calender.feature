Feature: Calender

  Background: client Login
    Given the client user is logged in with their account
    And the user is a client

  Scenario: Client want to see his calender with future events
    When the client specify he want to see his "future" events
    And the client requests to show his calender
    Then all events he booked will be shown with date,event name and id
    And the events must be in the future (upcoming events)

  Scenario: Client want to see his calender with past events
    When the client specify he want to see his "past" events
    And the client requests to show his calender
    Then all events he booked will be shown with date,event name and id
    And the events must be in the past (past events)

  Scenario: Client want all details about some event he is booked
    When the client specify the id of event he will see "2"
    And the client requests to show his event
    Then all the details of the event will be shown on the screen

  Scenario: Client want all details about some event with invalid id
    When the client specify the id of event he will see "E11"
    And the client requests to show his event
    Then an error message should be displayed "Invalid event id to show"

  Scenario: Client want all details about some event that is not on the list
    When the client specify the id of event he will see "1"
    And the client requests to show his event
    Then an error message should be displayed "This Event is not on the list"

  Scenario: Some user want to show all upcoming events from all organizers
    Given the user has a valid username "Admin" and password "123456"
    When the user logs in with valid credentials
    And the the user requests to show all upcoming events
    Then all upcoming events must be shown on the screen


