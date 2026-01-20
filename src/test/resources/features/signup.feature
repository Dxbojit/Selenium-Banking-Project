@smoke @signup
Feature: Sign Up Page Validation

  Background:
    Given the user is on the Sign Up page

  @Positive
  Scenario: User creates an account successfully
    When the user enters "Tom Tester" as Full Name
    And the user enters "newuser@test.com" as Email
    And the user enters "123456" as Pin Code
    And the user enters "Pass123!" as Password
    And the user enters "Pass123!" as Confirm Password
    And the user selects a Date of Birth for age 25
    And the user checks the Terms and Conditions
    And the user clicks Create Account
    Then the user should be redirected to the Dashboard Page

  @Negative
  Scenario: Validation error for password mismatch
    When the user enters "Pass123!" as Password
    And the user enters "DifferentPass!" as Confirm Password
    And the user clicks Create Account
    Then the user should see a password mismatch error
    
    
  @Negative
  Scenario: User cannot create an account without Pin Code
    When the user enters "Tom Tester" as Full Name
    And the user enters "nopincode@test.com" as Email
    # We intentionally SKIP entering the Pin Code here
    And the user enters "Pass123!" as Password
    And the user enters "Pass123!" as Confirm Password
    And the user selects a Date of Birth for age 25
    And the user checks the Terms and Conditions
    And the user clicks Create Account
    # We expect the app to stop us, but it won't. This step will FAIL.
    Then the user should remain on the Sign Up page