package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.FavouritesPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertTrue;

public class FavouritesPageSelectAllAndDeleteTest_StepDefinition {
    private final FavouritesPage favouritesPage = new FavouritesPage();

    @And("User click on Select All button")
    public void clickSelectAll() {
        $("#selectAllAction")
                .shouldBe(visible, enabled)
                .click();
    }

    @And("User click on Delete marked button")
    public void clickDeleteSelected() {
        $("#deleteSelectedAction")
                .shouldBe(visible, enabled)
                .click();
        $("div.boxed.ticket-info").should(disappear);
    }

    @Then("User verify that no favourites vehicle at page")
    public void userVerifyThatNoFavouritesVehicleAtPage() {
        assertTrue(favouritesPage.isFavouritesPageEmpty(),
                "Favourites page should be empty after deletion");
    }

}
