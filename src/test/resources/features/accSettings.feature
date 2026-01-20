<<<<<<< HEAD
Feature: Account Settings Page Functionality
=======
@Account_Settings
Feature: Account Settings Page UI Verification

  As a registered user of TDD Bank
  I want to navigate to my Account Settings
  So that I can verify my personal details, security options, and account type are visible
>>>>>>> e1e3038 (Completed Transactions Page Test)

  Background: 
    Given the user is logged into TDDBank 
    And the user navigates to "Account Settings" from the navbar

  Scenario: Verify UI layout and section visibility
    Then the user should be redirected to the settings page
   
    And the following sections should be visible:
      | Section Name      | Visibility |
      | Personal Details  | Visible    |
      | Security          | Visible    |
      | Account Type      | Visible    |
    
  Scenario Outline: Successfully update profile information
    
    When the user clicks the "Edit" option for "Personal and Contact Info"    
    And the user updates the "<Field>" field with "<NewValue>"    
    And the user clicks the "Save Changes" button    
    Then a success message "Account details updated successfully!" should be displayed
       Examples:
      | Field         | NewValue                      |
      | FULL NAME     | Demo User                     |
      | MOBILE NUMBER | +1 555-0123-456               |
      | ADDRESS       | 123 Banking Lane, Test Colony |