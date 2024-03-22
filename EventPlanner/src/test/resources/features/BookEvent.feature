Feature: Book Event
  As a client
  I want to be able to view upcoming events and book one of them
  So that I can attend the event of my choice

  Background: client Login
    Given the client user is logged in with their account
    And the user is a client

  Scenario: View upcoming events
    When the client choose to book an event
    Then the client should see a list of upcoming events

  Scenario: Book an event successfully
    When the client chooses an event by its ID "2"
    And the client requests to book an event
    Then the client should see a confirmation message "You have successfully booked the event"
    And the client should receive an email confirming the booking

  Scenario: Book an event and the user is not client
    When the user logs in as a role not client
    And the client requests to book an event
    Then the client should see an error message "You Are not Client"

  Scenario: Book an event with invalid event ID
    When the client enters an invalid event ID "E11"
    And the client requests to book an event
    Then the client should see an error message "Invalid event ID"

  Scenario: Book an event that is already fully booked by the client
    When the client chooses an event by its ID "2"
    And the client requests to book an event
    Then the client should see an error message "You Have Already Booked this event"

  Scenario: Book an event without selecting any event
    When the client does not select any event
    And the client requests to book an event
    Then the client should see an error message "Please select an event to book."


  Scenario: Book an event when there are no upcoming events
    Given there are no upcoming events
    When the client requests to book an event
    Then the client should see a message "There are no upcoming events to book."

  Scenario: Book an event that is not upcoming
    When the client chooses an event by its ID "1"
    And the client requests to book an event
    Then the client should see a message "You Can't Book Event not from List."





