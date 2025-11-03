Feature: HomePage | Download From GooglePlay

  Scenario: The user clicks Install via Google Play and is redirected to the download page.
    Given User open website AUTO.RIA
    When User click GooglePlay download button
    Then User goes to the download page and makes sure it is correct.
