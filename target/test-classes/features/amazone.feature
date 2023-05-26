Feature: Amazone

  Scenario: Select Nikon product in Amazon
    Given I go to the Amazon
    And I search Nikon
    And I sort the result from highest price to slowest
    When I click the 2nd result
    Then I can see the product