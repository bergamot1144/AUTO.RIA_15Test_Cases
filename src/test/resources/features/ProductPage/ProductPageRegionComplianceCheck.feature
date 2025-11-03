Feature: ProductPage | Region Compliance Check

  Scenario: User check location at product page and extended story page
    Given User open website AUTO.RIA
    When User click on first product on main page
    And User check geolocation of this vehicle
    And User click extended story button
    Then User check location at Technical verification at a Certified AUTO.RIA center
