Feature: SignUp

  Scenario Outline: User SignUp
    Given the user is on the SignUp page
    When the user provides available username "Amr"
    And the user provides valid email format "Amr2003@gmail.com"
    And the user provides valid role <role>
    And the user provides strong password "Amr123456789"
    And hit the register button
    Then the user account is created successfully
  Examples:
    |role|
    |"Client"|
    |"Service Provider"|


  Scenario: SignUp with missing email
    Given the user is on the SignUp page
    When the user submits the SignUp form with missing email
    Then the system displays error messages for the missing fields "some fields are missing"

  Scenario: SignUp with existing contact email
    Given the user is on the SignUp page
    And an existing user with email "Ahmad123@Gmail.com"
    When the user provides a contact email "Ahmad123@Gmail.com" that is already registered
    Then the system displays an existing contact email error message "the email you registred is not available"

  Scenario: SignUp with invalid email format
    Given the user is on the SignUp page
    When the user provides an invalid contact email format "Omar1243"
    Then the system displays an invalid email format error message "the email you registred is invalid format"

  Scenario: SignUp with missing username
    Given the user is on the SignUp page
    When the user provides valid email format "Omar123@gmail.com"
    And the user submits the SignUp form with missing username
    Then the system displays error messages for the missing fields "some fields are missing"

  Scenario: SignUp with existing username
    Given the user is on the SignUp page
    And an existing user with username "Ahmad"
    When the user provides valid email format "Omar123@gmail.com"
    And the user provides the existing username "Ahmad"
    Then the system displays an error message for the existing username "the username you entered is already registred"


  Scenario: SignUp with missing password
    Given the user is on the SignUp page
    When the user provides valid email format "Omar123@gmail.com"
    And the user provides available username "Amr"
    And the user submits the SignUp form with missing password
    Then the system displays error messages for the missing fields "some fields are missing"

  Scenario: SignUp with weak password
    Given the user is on the SignUp page
    When the user provides valid email format "Omar123@gmail.com"
    And the user provides available username "Amr"
    And the user provides a password that is too short "123"
    Then the system displays a weak password error message says "your password is weak"

  Scenario: SignUp with invalid role
    Given the user is on the SignUp page
    When the user provides valid email format "Omar123@gmail.com"
    And the user provides available username "Amr"
    And the user provides strong password "Amr123456789"
    And the user provides an invalid role "Admin"
    Then the system displays an error message for the invalid role "you entered an invalid role"




