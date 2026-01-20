<<<<<<< HEAD
@smoke @regression @login
=======
>>>>>>> e1e3038 (Completed Transactions Page Test)
Feature: Login Functionality for TDD Bank

  Background: 
    Given the user is on the TDDBank login page

<<<<<<< HEAD
  @smoke @regression @positive
=======
>>>>>>> e1e3038 (Completed Transactions Page Test)
  Scenario Outline: Successful login with valid role-based credentials
    When the user logs in using email "<email>" and password "<password>"
    Then the user should be redirected to the dashboard

    Examples:
      | email               | password     |
      | admin@tddbank.com   | 123456       |
      | manager@tddbank.com | bankmanager  |
      | user@tddbank.com    | password     |
<<<<<<< HEAD
  
  @regression @negative
=======

>>>>>>> e1e3038 (Completed Transactions Page Test)
  Scenario Outline: Unsuccessful login with invalid credentials
    When the user logs in using email "<email>" and password "<password>"
    Then the user should see an error message 

    Examples:
      | email                   | password     |
      | admin@tddbank.com       | password     |  # Correct email, wrong pass
      | unknown@tddbank.com     | bankmanager  |  # Unregistered email
      | user1@tddbank.com       | 123456       |  # Non-existent user