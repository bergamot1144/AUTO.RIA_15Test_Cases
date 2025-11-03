Feature: HomePage | Open Dom.Ria website

  Scenario: User open main page, click Real Estate at header and redirect to Dom.Ria website
    Given User open website AUTO.RIA
    When User click new cars button at header and redirect to the new page
    And User click RealEstate button at header and redirect to Dom.Ria website
    Then User makes sure it is correct.