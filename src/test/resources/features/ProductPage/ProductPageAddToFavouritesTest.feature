Feature: ProductPage | Add to Favourites functionality

  Scenario: User adds vehicle to favourites and verify it
    Given User open website AUTO.RIA
    When User click on first product on main page
    And User click heart button
    And User open favourites page
    Then User checks that vehicle is on the page