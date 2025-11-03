package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CatalogPage;
import pages.HomePage;

import static org.testng.Assert.assertTrue;

public class HomePageTryFiltersTest_StepDefinition {
    private final HomePage homePage = new HomePage();
    private final CatalogPage catalogPage = new CatalogPage();

    @Given("User open website AUTO.RIA")
    public void userOpenHomePage() {
        homePage.initMainPage();
    }

    @When("User enter type {string}, brand {string}, model {string}, year from {string} to {string}, price: from {string} to {string}, region {string}, fuel {string}, gearbox {string}")
    public void userInputFilters(String typeOfVehicle, String brand, String model, String minYear, String maxYear,
            String minPrice, String maxPrice, String region, String fuelType, String gearboxType) {
        homePage.FilterButtonTypeOfVehicleClick();
        homePage.voteForVehicleTypeClick(typeOfVehicle);
        homePage.FilterButtonBrandAndModelClick();
        homePage.voteForBrandAndModelClick(brand, model);
        homePage.FilterButtonYearClick();
        homePage.voteForMinYearClick(Integer.parseInt(minYear));
        homePage.voteForMaxYearClick(Integer.parseInt(maxYear));
        homePage.FilterButtonPriceClick();
        homePage.voteForMinPriceClick(Integer.parseInt(minPrice));
        homePage.voteForMaxPriceClick(Integer.parseInt(maxPrice));
        homePage.FilterButtonRegionClick();
        homePage.voteForRegion(region);
        homePage.FilterButtonFuelClick();
        homePage.voteForFuel(fuelType);
        homePage.FilterButtonGearboxClick();
        homePage.voteForGearbox(gearboxType);
    }

    @And("User pick search button")
    public void userPickSearchRedButton() {
        homePage.RedSearchButtonClick();
    }

    @And("User open catalog page")
    public void pickHeartButton() {
        catalogPage.waitForCatalogPageLoad();
    }

    @Then("User check filters at chipsContainer {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void user_check_filters_at_chips_container(String typeOfVehicle, String brand, String model, String minYear,
            String maxYear, String minPrice, String maxPrice, String region, String fuelType, String gearboxType) {

        assertTrue(catalogPage.isChipsContainerVisible(), "Chips container should be visible");
        assertTrue(catalogPage.containsFilterChip(typeOfVehicle), "Type of vehicle filter should be present");
        assertTrue(catalogPage.containsFilterChip(brand), "Brand filter should be present");
        assertTrue(catalogPage.containsYearRange(minYear, maxYear), "Year range should be present");
        assertTrue(catalogPage.containsPriceRange(minPrice, maxPrice), "Price range should be present");
        assertTrue(catalogPage.containsFilterChip(region), "Region filter should be present");
        assertTrue(catalogPage.containsFilterChip(fuelType), "Fuel type filter should be present");
        assertTrue(catalogPage.containsFilterChip(gearboxType), "Gearbox type filter should be present");
    }
}
