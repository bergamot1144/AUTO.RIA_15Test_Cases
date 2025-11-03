package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.ProductPage;
import pages.FavouritesPage;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertTrue;

public class ProductPageAddToFavouritesTest_StepDefinition {
    HomePage homePage = new HomePage();
    ProductPage productPage = new ProductPage();
    FavouritesPage favouritesPage = new FavouritesPage();

    @When("User click on first product on main page")
    public void userClickOnFirstProductOnMainPage() {
        executeJavaScript("document.body.style.zoom = '0.5'");
        sleep(500);

        homePage.openFirstItemAtHomePage();
        sleep(3000);
    }

    @And("User click heart button")
    public void userClickHeartButton() {
        productPage.heartButtonClick();
        sleep(5000);
    }

    @And("User open favourites page")
    public void userOpenFavouritesPage() {
        homePage.ButtonOpenFavouritesClick();
    }

    @Then("User checks that vehicle is on the page")
    public void userChecksThatVehicleIsOnThePage() {
        sleep(1000);
        int favouritesCount = favouritesPage.getFavouritesCountFromHeader();
        assertTrue(favouritesCount > 0,
                "No vehicles found in favourites. Expected 1, but found " + favouritesCount);

    }
}
