Feature: FavouritesPage | Use filters on favourites page and verify correctly work

  Scenario: User adds vehicles to favourites and verifies them
    Given User open website AUTO.RIA
    When User click on 6 heart button at cars ads
    And User go to favourites page clicking heart button
    When User click brand filter button and select first brand value
    And User click blue search button
    Then The user confirms that all advertisements in the catalogue contain the name of the selected brand