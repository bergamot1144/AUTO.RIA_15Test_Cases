package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CatalogPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import org.openqa.selenium.JavascriptExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatalogPageBatteryCapacityAndPowerReserve_StepDefinition {
    private static final Logger logger = LoggerFactory.getLogger(CatalogPageBatteryCapacityAndPowerReserve_StepDefinition.class);
    CatalogPage catalogPage = new CatalogPage();

    private void setZoomLevel(double zoomLevel) {
        logger.info("Setting page zoom level to: " + zoomLevel);
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("document.body.style.zoom='" + zoomLevel + "'");
        sleep(1000);
        logger.info("Page zoom level set successfully");
    }

    @When("User chooses the type of engine Electro")
    public void userChoosesTheTypeOfEngine() {
        logger.info("Starting selection of engine type 'електро'");
        setZoomLevel(0.75);
        catalogPage.selectFuelType("електро");
        logger.info("Engine type 'електро' selected successfully");
        sleep(1000);
        logger.info("Waiting completed");
    }

    @And("User check the Battery capacity subfilter is displayed")
    public void userCheckTheBatteryCapacitySubfilterIsDisplayed() {
        logger.info("Checking display of subfilter 'Ємність акумулятору'");
        $$("span.common-text")
                .findBy(text("Ємність акумулятору"))
                .shouldBe(visible);
        logger.info("Subfilter 'Ємність акумулятору' found and displayed");
    }

    @When("User changes the engine type to Gasoline")
    public void userChangesTheEngineTypeTo() {
        logger.info("Starting engine type change from 'електро' to 'Бензин'");
        logger.info("First, deselecting 'електро'");
        catalogPage.selectFuelType("електро");
        sleep(1000);
        logger.info("Now selecting 'Бензин'");
        catalogPage.selectFuelType("Бензин");
        sleep(1000);
        logger.info("Engine type change completed successfully");
    }

    @Then("User check the subfilter Engine capacity is displayed")
    public void userCheckTheEngineCapacitySubfilterIsDisplayed() {
        logger.info("Checking display of subfilter 'Витрата палива'");
        $("#fuelConsTitle span.common-text")
                .shouldBe(visible)
                .shouldHave(text("Витрата палива"));
        logger.info("Subfilter 'Витрата палива' found and displayed correctly");
    }

    @And("User check that Battery capacity subfilter is not displayed")
    public void userCheckThatSubfilterIsNotDisplayed() {
        logger.info("Checking that subfilter 'Ємність акумулятору' is NOT displayed");
        $$("span.common-text")
                .findBy(text("Ємність акумулятору"))
                .shouldNot(exist);
        logger.info("Confirmed: subfilter 'Ємність акумулятору' is not present on the page");
    }
}
