Feature: CatalogPage | The display of filters depends on the type of engine

  Scenario: The user sets the fuel type filter and checks
  that the corresponding range filters appear for the internal combustion engine and electric motor.
    Given User open website AUTO.RIA
    When User pick search button
    And User open catalog page
    When User chooses the type of engine Electro
    And User check the Battery capacity subfilter is displayed
    When User changes the engine type to Gasoline
    Then User check the subfilter Engine capacity is displayed
    And User check that Battery capacity subfilter is not displayed
