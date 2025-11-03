Feature: CatalogPage | Test Sort from expensive to cheap

  Scenario: Verify "Clear all" button at Catalog page
    Given User open website AUTO.RIA
    When User pick search button
    And User open catalog page
    When User click on sort button
    And Pick 3 sort button
    Then The user makes sure that each subsequent card is cheaper than the previous one
