Feature: CatalogPage | Open old website version

  Scenario: User open catalog page and pick button to visit old website version
    Given User open website AUTO.RIA
    When User pick search button
    And User open catalog page
    And User click return to previous version button
    Then Verify thar old website version is opened