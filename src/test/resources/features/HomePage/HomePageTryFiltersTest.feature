Feature: HomePage | Filter functionality verification

  Scenario: Verify filter functionality on home page
    Given User open website AUTO.RIA
    When User enter type "Мото", brand "Harley-Davidson", model "Fat Boy", year from "2010" to "2025", price: from "10000" to "40000", region "Київська", fuel "Бензин", gearbox "Ручна / Механіка"
    And User pick search button
    And User open catalog page
    Then User check filters at chipsContainer "Мото", "Harley-Davidson", "Fat Boy", "2010", "2025", "10000", "40000", "Київська", "Бензин", "Ручна / Механіка"
