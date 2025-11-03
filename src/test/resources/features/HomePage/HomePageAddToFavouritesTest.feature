Feature: HomePage | Add to Favourites functionality

  Scenario: User adds vehicles to favourites and verifies them
    Given User open website AUTO.RIA
    When User click on 4 heart button at cars ads
    And User go to favourites page clicking heart button
    Then User checks that the selected vehicle is on the page
