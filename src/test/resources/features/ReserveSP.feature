Feature: Reserve Service Providers to Events

  Background: organizer Login
    Given the user is logged in with their account
    And the user is organizer

  Scenario: Organizer initiates service provider Reservation
    When the organizer selects to reserve a service provider
    Then the system should display a list of events related to him
    And the system should list all registered service providers with complete account

  Scenario: Organizer successfully reserves a service provider to an event
    When the organizer selects EventID "1" and ProviderName "FactoryX"
    Then the service provider is reserved to event
    And a confirmation message should be displayed "Service Provider has been reserved"
    And a notification will be added with all details


  Scenario: Attempting to reserve a service provider with invalid event ID
    When the organizer selects EventID "E98" and ProviderName "FactoryX"
    Then an error message should be displayed indicating "the event ID is invalid"

  Scenario: Attempting to reserve a service provider with non-existed service provider username
    When the organizer selects EventID "1" and ProviderName "oiuw"
    Then an error message should be displayed indicating "non-existed service provider"

  Scenario: Attempting to reserve a service provider with incomplete account
    When the organizer selects EventID "1" and ProviderName "Saif"
    Then an error message should be displayed indicating "incomplete account of service provider"





