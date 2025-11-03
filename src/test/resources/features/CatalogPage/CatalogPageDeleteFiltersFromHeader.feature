Feature: CatalogPage | Test "Clear All" button

  Scenario: Test "Clear all" button at Catalog page
    Given User open website AUTO.RIA
    When User enter type "Мото", brand "Harley-Davidson", model "Fat Bob", year from "2010" to "2025", price: from "10000" to "40000", region "Київська", fuel "Бензин", gearbox "Ручна / Механіка"
    And User pick search button
    And User open catalog page
    And User check filters at chipsContainer "Мото", "Harley-Davidson", "Fat Bob", "2010", "2025", "10000", "40000", "Київська", "Бензин", "Ручна / Механіка"
    Then User click clear all button and verify that all chips are disappear
