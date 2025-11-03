package steps;

import com.codeborne.selenide.ElementsCollection;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FavouritesPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertTrue;

public class FavouritesPageFiltersTest_StepDefinition {
    private String selectedBrand;
    FavouritesPage favouritesPage = new FavouritesPage();

    @When("User click brand filter button and select first brand value")
    public void userClickBrandFilterButtonAndSelectFirstBrandValue() {
        $("#brand")
                .shouldBe(visible, enabled)
                .selectOption(1);

        selectedBrand = $("#brand").getSelectedOptionText();

        if (selectedBrand.contains("(")) {
            selectedBrand = selectedBrand.substring(0, selectedBrand.indexOf("(")).trim();
        }
    }

    @And("User click blue search button")
    public void userClickBlueSearchButton() {
        favouritesPage.clickBlueSearchButton();
        sleep(1000);
    }

    @Then("The user confirms that all advertisements in the catalogue contain the name of the selected brand")
    public void theUserConfirmsThatAllAdvertisementsInTheCatalogueContainTheNameOfTheSelectedBrand() {
        ElementsCollection transportCards = $$("div.boxed.ticket-info");

        for (int i = 0; i < transportCards.size(); i++) {
            String title = transportCards.get(i).$("a.size18").getText();
            assertTrue(title.contains(selectedBrand),
                    "Advertisement should contain brand '" + selectedBrand + "', but found: " + title);
        }
    }

}
