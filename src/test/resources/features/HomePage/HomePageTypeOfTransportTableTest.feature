Feature: HomePage | Type of transport checker

  Scenario: User open full table with auto brands, choose some brand and check that all transport matches his choice
    Given User open website AUTO.RIA
    When User go to type of transport table and click Open All button
    And User choose "BMW"
    Then User checks that all transport matches his choice.
