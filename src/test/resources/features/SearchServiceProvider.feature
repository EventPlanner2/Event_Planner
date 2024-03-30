Feature: Search Service Provider

  Background:
    Given the user has selected to search a service provider

  Scenario: User searches for service providers based on location
    When the user selects location as the search criteria
    And the user enters "Nablus" as the location
    And the user has pressed to search
    Then the application should display a list of service providers in specified location

  Scenario: User searches for service providers based on type
    When the user selects type as the search criteria
    And the user enters "Chairs Provider" as the service type
    And the user has pressed to search
    Then the application should display a list of service providers with specified type

  Scenario: User searches for service providers based on price
    When the user selects price as the search criteria
    And the user enters "50" as the maximum price
    And the user has pressed to search
    Then the application should display a list of service providers with prices up to specified price


  Scenario: User searches for service providers without specifying a criteria
    When the user did not select any criteria
    Then the application should display an error message says "the user must choose one search criteria"

  Scenario: User searches for service providers with unexisted criteria
    When the user has selected an unexisted criteria "blabla"
    Then the application should display an error message says "the user must select an existed criteria"

  Scenario: User searches for service providers with an invalid location
    When the user selects location as the search criteria
    And the user enters an invalid location "New York"
    And the user has pressed to search
    Then the application should display an error message says "invalid location !"

  Scenario: User searches for service providers with an invalid type
    When the user selects type as the search criteria
    And the user enters an invalid service type "oiqwdi"
    And the user has pressed to search
    Then the application should display an error message says "invalid type !"

  Scenario: User searches for service providers with an invalid price
    When the user selects price as the search criteria
    And the user enters an invalid price "12qwd2"
    And the user has pressed to search
    Then the application should display an error message says "invalid price !"


