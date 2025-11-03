package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.ProductPage;
import pages.VehicleCheckerPage;

import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertEquals;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class ProductPageRegionComplianceCheck_StepDefinition {
    ProductPage productPage = new ProductPage();
    VehicleCheckerPage vehicleCheckerPage = new VehicleCheckerPage();

    private String geoFromProductPage;

    @And("User check geolocation of this vehicle")
    public void userCheckGeolocationOfThisVehicle() {
        geoFromProductPage = productPage.getGeoFromProductPage();
    }

    @And("User click extended story button")
    public void userClickExtendedStoryButton() {
        productPage.checkVehicleHistory();
        sleep(3000);
        switchTo().window(1);
    }

    @Then("User check location at Technical verification at a Certified AUTO.RIA center")
    public void userCheckLocationAtTechnicalVerificationAtACertifiedAUTORIACenter() {
        sleep(2000);
        executeJavaScript("document.body.style.zoom='0.5'");

        String selectedCity = vehicleCheckerPage.getSelectedCity();
        System.out.println("Geo from product page: '" + geoFromProductPage + "'");
        System.out.println("Selected city from select: '" + selectedCity + "'");

        assertEquals(
                selectedCity, geoFromProductPage,
                "Selected city '" + selectedCity + "' should match geo from product page '" + geoFromProductPage
                        + "'");

    }
}
