Feature: Complete Service Provider Account

  Scenario: Service provider creates and completes the account
    Given the service provider is newly registred
    When he logs in for the first time
    Then the system should prompt him to complete his account details (location,type,price)

  Scenario: Service provider completes account details
    Given the service provider has logged in
    When he completes the account details with valid inputs Location "Nablus" price "$99" type "Chairs Provider"
    Then the system should save the information
    And it should not ask for the same details on the second login

  Scenario: Service provider provides invalid location
    Given the service provider has logged in
    When he provides a invalid location "asdas"
    Then the system should display error messages "invalid location"

  Scenario: Service provider selects invalid type of service
    Given the service provider has logged in
    When he selects invalid type of service provider "qwqwe"
    Then the system should display error messages "invalid type of service"

  Scenario: Service provider provides invalid price
    Given the service provider has logged in
    When he provides invalid input price like "17d2"
    Then the system should display error messages "invalid format for price"



