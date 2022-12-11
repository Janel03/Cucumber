Feature: Dashboard functionality

  @sprint3 @regression @tc1106
  Scenario: Verify dashboard
    #Given user is navigated to HRMS application
    When user enters valid username and valid password
    And user clicks on login button
    Then user is successfully logged in
    Then user verifies dashboard page
    Then user verifies all the dashboard tabs
      |Admin|PIM|Leave|Time|Recruitment|Performance|Dashboard|Directory|

