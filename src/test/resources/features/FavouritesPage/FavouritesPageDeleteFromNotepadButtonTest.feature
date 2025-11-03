Feature: FavouritesPage | Delete from notepad button test

  Scenario: User adds vehicles to favourites and verifies them
    Given User open website AUTO.RIA
    When User click on 4 heart button at cars ads
    And User go to favourites page clicking heart button
    When User clicks delete on the first 4 ads
    Then User verify that function work correctly
