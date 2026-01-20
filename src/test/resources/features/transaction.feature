@regression @movemoney
@Transaction
Feature: Money Transfer Functionality
  This feature verifies different types of money transfers and beneficiary management using data-driven tests

  Background:
    Given the user is logged into the application
    And the user is on the Money Transfer page

  # =========================================
  # TC_TRFR_01 - Transfer to Saved Beneficiary
  # =========================================

  @regression @savedBeneficiary
@Quick_Transfer
  Scenario Outline: Transfer to saved beneficiary
    When the user selects a saved beneficiary
    And the user enters amount "<amount>"
    And the user clicks on Transfer Now
    Then the transfer result should be "<result>"

    Examples:
      | amount | result  |
      | 500    | success |
      | 0      | failure |
      | -100   | failure |
      

  # ===========================
  # TC_TRFR_02 - Other Bank
  # ===========================

  @regression @otherBank
  @New_Transfer_Other
  Scenario Outline: Transfer to other bank
    When the user navigates to "New Transfer"
    And the user selects "Other Bank"
    And the user enters "<beneficiaryType>" beneficiary details
    And the user enters amount "<amount>"
    And the user clicks on Confirm & Transfer
    Then the transfer result should be "<result>"

    Examples:
      | beneficiaryType | amount | result  |
      | valid           | 1200   | success |
      | invalid         | 1200   | failure |
      | valid           | 0      | failure |
      | invalid         | 0      | failure |
      
      

  # ===========================
  # TC_TRFR_03 - Own Account
  # ===========================

  @regression @ownAccount
  @New_Transfer_Own
  Scenario Outline: Own account transfer
    When the user navigates to "New Transfer"
    And the user selects "Own Account"
    And the user selects a secondary account
    And the user enters amount "<amount>"
    And the user clicks on Confirm & Transfer
    Then the transfer result should be "<result>"
    

    Examples:
      | amount | result  |
      | 5000   | success |
      | 0      | failure |
      | -200   | failure |

  @OwnTransferNegative
  Scenario: Owntransfer failed due to no account selection
  When the user navigates to "New Transfer"
  And the user selects "Own Account"
  When the user does not select any account
  And the user enters amount "11000"
  And the user clicks on Confirm & Transfer
  Then the transfer result should be "failure"

  # ===========================
  # TC_TRFR_04 - Add Beneficiary
  # ===========================

  @regression @addBeneficiary
  @Add_Beneficiary
  Scenario Outline: Add new beneficiary
    When the user navigates to "Manage Beneficiaries"
    And the user clicks on Add New
    And the user enters "<beneficiaryType>" new beneficiary details
    And the user clicks on Save
    Then the beneficiary creation result should be "<result>"

    Examples:
      | beneficiaryType | result  |
      | valid           | success |
      | invalid         | failure |
      
  @Delete_Beneficiary
  Scenario: Delete beneficiary
    When the user navigates to "Manage Beneficiaries"
    And the user clicks on Delete button
    Then the beneficiary name is not visible in the list
    
  @Transfer_Button_Check
  Scenario: Verifying Transfer button functionality
  When the user navigates to "Manage Beneficiaries"
  And the user clicks on Transfer button
  Then the user is redirected to Quick Transfer tab with the beneficiary name selected