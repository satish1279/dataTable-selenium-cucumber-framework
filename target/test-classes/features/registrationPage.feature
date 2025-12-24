Feature: User registration and profile update

  Scenario: Register a new user
    Given User is on the registration page
    When User enters the following details:
      | field            | value     |
      | Login            | Cenaaqqww |
      | First Name       | Doe       |
      | Last Name        | john      |
      | Password         | Pass@1234 |
      | Confirm Password | Pass@1234 |
    And User clicks on the Register button
    Then The registration should be successful

  @profile
  Scenario: Update user profile details
    Given User is logged in
    And User is naviagted to Profile page
    When User enters the following updated details:
      | field   | value          |
      | Gender  | Male           |
      | Age     |             37 |
      | Address | Delhi          |
      | Phone   |     9876543210 |
      | Hobby   | Reading Comics |
    And User clicks on the Save button
    Then the profile should be updated successfully
