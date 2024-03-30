Feature: Calender

  Background: client Login
    Given the client user is logged in with their account
    And the user is a client

  Scenario: Client want to cancel event he booked
    When the client chooses choice cancel booking event
    Then all upcoming events he booked will be shown with date,event name and id

  Scenario: Client want to cancel specfic event
    When the client specify the id of the the event he booked "2"
    And the client requests to cancel booking of the event
    Then booking of the event must be cancelled


  Scenario: Client want to cancel booking of event with invalid id
    When the client specify the id of event he will cancel "E11"
    And the client requests to cancel booking of the event
    Then an error message should be displayed cancel booking "Invalid event id to cancel booking"

  Scenario: Client want to cancel booking of event with id of event that are not upcoming
    When the client specify the id of event he will cancel booking "1"
    And  the client requests to cancel booking of the event
    Then an error message should be displayed cancel booking "This Event has gone"



