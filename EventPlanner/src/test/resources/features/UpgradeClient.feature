Feature: UpgradeClient
  As a client, I want to upgrade my account to an organizer to be able to create events.

  Background:
    Given the user is logged in as a client

  Scenario: User upgrades account successfully
    When the user selects Upgrade your account from the menu
    Then the account should be successfully upgraded to organizer status
    And the user should receive a confirmation message "Your Account has been upgraded"

  Scenario: User already has organizer privileges
    Given the user is logged in as an organizer
    When the user selects Upgrade your account from the menu
    Then the system should inform the user that "the account is already upgraded"

  Scenario: User cancels account upgrade
    When the user selects Upgrade your account from the menu
    And then cancels the upgrade process
    Then the account should remain as a client

  Scenario: User attempts to upgrade without being logged in
    Given the user is not logged in
    When the user selects Upgrade your account from the menu
    Then the system should prompt the user to log in first "You need to login first"

  Scenario Outline: User tries to upgrade from an invalid state
    Given the user is logged in as a <state> with <role> with <username> with <password>
    When the user selects Upgrade your account from the menu
    Then the system should inform the user with invalid role "only clients can upgrade their accounts"

  Examples:
    |state| |role| |username| |password|
    |"Service Provider"| |"s"| |"FactoryX"| |"FactoryX123"|
    |"Admin"| |"a"| |"Admin"| |"123456"|