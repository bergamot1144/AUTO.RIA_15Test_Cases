Feature: FavouritesPage | Delete all favourites vehicle test

  Scenario: User adds vehicles to favourites and verifies them
    Given User open website AUTO.RIA
    When User click on 4 heart button at cars ads
    And User go to favourites page clicking heart button
    When User checks that the selected vehicle is on the page
    And User click on Select All button
    And User click on Delete marked button
    Then User verify that no favourites vehicle at page