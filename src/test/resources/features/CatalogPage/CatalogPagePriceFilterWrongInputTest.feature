Feature: CatalogPage | Wrong input in price filter

  Scenario: User enters a "From" value greater than the "To" value.
    Given User open website AUTO.RIA
    When User pick search button
    And User open catalog page
    When User enters "10000" in the From field
    And User enters "5000" in the To field
    Then User verify that From field should be less than To field