@regression @security
Feature: Security and Privacy Settings
  As a user
  I want to manage my security and privacy settings
  So that I can control preferences and deactivate my account if needed

  Background:
    Given the user is logged in
    And the user is on the Security page

  # ----------------------------
  # Scenario 1: Toggle Buttons
  # ----------------------------
  Scenario: Verify preference toggle buttons behavior
    Then the page should display three toggle buttons

    When the user clicks on a toggle button
    Then the button should slide to the Off position

  # -----------------------------------
  # Scenario 2: Deactivate Account - OK
  # -----------------------------------
  Scenario: Deactivate account by accepting confirmation
    Then the red Deactivate button should be visible

    When the user clicks on the Deactivate button
    Then the alert text should be clear and legible

    Then the account gets deactivated and the user is redirected to login page
    

 