package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CatalogPage;
import pages.HomePage;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertTrue;

public class HomePageTypeOfTransportTableTest_StepDefinition {
    private final HomePage homePage = new HomePage();
    private final CatalogPage catalogPage = new CatalogPage();

    @When("User go to type of transport table and click Open All button")
    public void userGoToTypeOfTransportTableAndClickOpenAllButton() {
        homePage.tableTypeOfTransportClickAllButton();
    }

    @And("User choose {string}")
    public void chooseBrand(String brand) {
        executeJavaScript("document.body.style.zoom = '0.5'");
        sleep(500);

        homePage.selectBrandFromTable(brand);
    }

    @Then("User checks that all transport matches his choice.")
    public void userChecksThatAllTransportMatchesHisChoice() {
        System.out.println("=== STARTING BRAND VERIFICATION ===");

        executeJavaScript("document.body.style.zoom = '0.25'");
        sleep(500);

        catalogPage.waitForCatalogPageLoad();

        String currentUrl = com.codeborne.selenide.Selenide.executeJavaScript("return window.location.href;");
        System.out.println("Current URL for checking advertisements: " + currentUrl);

        int ticketItemsCount = com.codeborne.selenide.Selenide.$$("section.ticket-item").size();
        System.out.println("Found section.ticket-item advertisements on page: " + ticketItemsCount);

        if (ticketItemsCount == 0) {
            System.out.println("WARNING: No section.ticket-item advertisements found on page");

            int altCount = com.codeborne.selenide.Selenide.$$("div[id^='Auto'][class*='wrapper-elevation']").size();
            System.out.println("Found advertisements with alternative selector: " + altCount);
        }

        System.out.println("=== RUNNING BRAND VERIFICATION ===");
        boolean result = catalogPage.verifyAllTransportMatchesBrand();
        System.out.println("=== VERIFICATION RESULT: " + (result ? "SUCCESS" : "ERROR") + " ===");

        assertTrue(result, "All transport should match the selected brand");
    }
}
