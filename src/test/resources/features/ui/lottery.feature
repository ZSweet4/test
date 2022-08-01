Feature: Lottery Feature

  @Lottery
  Scenario: Capture Winning Numbers
    Given user navigates to the "https://www.usamega.com/powerball/results" lottery page
    And user is on Lottery page
    Then page displays winning numbers