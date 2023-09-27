@reg
Feature: Validate KWI Loyalty page
Background:
  Given I navigated to KWI application

  Scenario: Validate text values
    Then I should be able to see a text RETAIL OMNICHANNEL PLATFORM
    Then I scroll down and verify text ALL-IN-ONE OMNICHANNEL SOLUTION
    Then I scroll down and verify phone and email

    And I click on hamburger menu, click on Platform & Services and then Loyalty
    Then I should be able to scroll down to Get More Control
    And I print out paragraph on Get More Section
    Then I scroll down and Ebooks and click on '9' Noble Truth of Modern Retailinig
    And I fill up the form and click on Download
    Then I validate it opens PDF page

