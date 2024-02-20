Feature: Login
  Description: Login page for the system
  Actor : Admin , Service-Provider , User

  Scenario: Successful Login
    When I enter correct username and correct password
    And I click on the login button
    Then login screen switches to main screen

  Scenario: Failed Login
    When I enter wrong username or wrong password
    And I click on the login button
    Then error message indicating failed login is prompted
