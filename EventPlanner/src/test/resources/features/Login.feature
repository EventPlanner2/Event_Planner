Feature: Login

  Background:
    Given the user has not logged in yet
  Scenario: Successful login
    Given the user has a valid username "Admin" and password "123456"
    When the user logs in with valid credentials
    Then the user should be logged in successfully

  Scenario: Failed login with incorrect password
    Given the user has a valid username "FactoryX" and incorrect password "pass"
    When the user logs in with invalid password
    Then the user should see an error message "Login Failed"

  Scenario: Failed login with incorrect username
    Given the user has an invalid username "test" and valid password "123456"
    When the user logs in with invalid username
    Then the user should see an error message "Login Failed"

  Scenario: Failed login with both incorrect username and password
    Given the user has an invalid username "test" and incorrect password "test22"
    When the user logs in with both invalid username and password
    Then the user should see an error message "Login Failed"

  Scenario: User provides empty credentials
    Given the user has empty username and password
    When the user tries to log in with empty credentials
    Then the user should see an error message "Login Failed"
