Feature: Help and Support Page Functionality

  Background:
    Given the user is logged into TDDBank
    And the user logs in using email "user@tddbank.com" and password "password"
    And the user navigates to the "Help and Support" page

  Scenario: Verify FAQ Accordion Interaction
    Given the user is on the FAQ section
    And the list of questions is displayed with collapsed answers
    When the user clicks the dropdown icon beside an FAQ question
    Then the answer section should expand and be clearly visible
    And the answer text should be legible and accurate
    When the user clicks the dropdown icon again
    Then the answer section should be hidden from view

  Scenario: Verify Search Bar Filtering for KYC and PIN
    Given the FAQ search bar is empty and active
    When the user types "kyc" in the search bar
    Then the list should be filtered in real-time
    And only questions containing the term "KYC" should be displayed
    When the user clears the search bar
    And the user types "pin" in the search bar
    Then the system should display relevant questions regarding "PIN" security

  Scenario: Verify Support Contact Channels
    When the user scrolls to the bottom of the FAQ page
    Then the "Live Chat" and "Call Now" buttons should be visible
    When the user clicks the "Live Chat" button
    Then the support chat widget should open for communication
    When the user clicks the "Call Now" button
    Then the system should trigger a call link or display the support number