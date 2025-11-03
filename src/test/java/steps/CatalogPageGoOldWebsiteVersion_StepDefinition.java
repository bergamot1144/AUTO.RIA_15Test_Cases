package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import static org.testng.Assert.assertTrue;
import pages.CatalogPage;

import static com.codeborne.selenide.Selenide.sleep;

public class CatalogPageGoOldWebsiteVersion_StepDefinition {
    CatalogPage catalogPage = new CatalogPage();

    @And("User click return to previous version button")
    public void userClickReturnToPreviousVersionButton() {
        catalogPage.visitOldWebsiteVersion();
        catalogPage.redButtonSubmitOpenOldWebsiteVersion();
        sleep(2000);
    }

    @Then("Verify thar old website version is opened")
    public void verifyTharOldWebsiteVersionIsOpened() {
        boolean isButtonVisible = catalogPage.isNewWebsiteVersionButtonVisible();
        sleep(2000);
        assertTrue(isButtonVisible, "Button 'Перейти до нової версії' is not visible on page");
    }
}
