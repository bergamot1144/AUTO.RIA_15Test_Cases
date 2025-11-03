package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import pages.HomePage;
import pages.FavouritesPage;
import pages.TransportData;

import static org.testng.Assert.assertTrue;

public class HomePageAddToFavouritesTest_StepDefinition {
    private final HomePage homePage = new HomePage();
    private final FavouritesPage favouritesPage = new FavouritesPage();

    @When("User click on {int} heart button at cars ads")
    public void userClickOnHeartButtonAtCarsAds(int heartCount) {
        homePage.clickHeartButtons(heartCount);
    }

    @And("User go to favourites page clicking heart button")
    public void userGoToFavouritesPage() {
        homePage.ButtonOpenFavouritesClick();
    }

    @Then("User checks that the selected vehicle is on the page")
    public void userChecksSelectedVehicleOnPage() {
        $("h1.head-notepad span#advertisementsCounter").shouldBe(visible);
        var addedTransports = homePage.getAddedToFavorites();

        for (TransportData transport : addedTransports) {
            assertTrue(favouritesPage.containsTransport(transport),
                    "Transport should be in favourites: " + transport.toString());
        }

        int expectedCount = addedTransports.size();
        int actualCount = favouritesPage.getFavouritesCountFromHeader();
        assertTrue(actualCount == expectedCount,
                "Expected " + expectedCount + " items in favourites, but found " + actualCount);
    }

    public int getAddedToFavoritesCount() {
        return homePage.getAddedToFavorites().size();
    }
}