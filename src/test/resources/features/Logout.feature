Feature: Logout

  Scenario: Successful Logout
    Given the user is logged in
    When the user selects the option to log out
    Then the user should be successfully logged out and show message "Successful Logout"

  Scenario: Failed Logout Attempt
    Given the user is logged in
    When the user selects the option to log out and encounters a logout issue happened
    Then the system should display a warning message about the logout failure "Failed Logout"

