Feature: US-321 Searching the employee

  @batch14 @sprint4
  Scenario: Search employee by id
    Given user is navigated to HRMS application
    When user enters valid username and valid password
    And user clicks on login button
    Then user is successfully logged in
    When user clicks on PIM option
    And user clicks on Employee List option
    When user enters valid employee id
    And user clicks on search button
    Then user sees employee information is displayed