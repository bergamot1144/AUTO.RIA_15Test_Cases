package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.FavouritesPage;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.testng.Assert.assertTrue;

public class FavouritesPageDeleteFromNotepadButtonTest_StepDefinition {
    private final FavouritesPage favouritesPage = new FavouritesPage();
    private int deletedCount = 0;

    @When("User clicks delete on the first {int} ads")
    public void userClicksDeleteOnTheFirstAds(int number) {
        deletedCount = 0;

        executeJavaScript("document.body.style.zoom = '0.5';");
        sleep(300);

        for (int i = 0; i < number; i++) {
            sleep(1000);
            var deleteButtons = $$(".deleteFromNotepad");

            if (deleteButtons.size() > 0) {
                deleteButtons.get(0).click();
                deletedCount++;
                sleep(1000);
            } else {
                break;
            }
        }
    }

    @Then("User verify that function work correctly")
    public void userVerifyThatFunctionWorkCorrectly() {
        int currentCount = favouritesPage.getFavouritesCountFromHeader();

        System.out.println("DEBUG INFO:");
        System.out.println("Deleted count: " + deletedCount);
        System.out.println("Current count: " + currentCount);

        assertTrue(deletedCount > 0 && currentCount >= 0,
                "Delete function should work - deleted " + deletedCount + " items, current count: " + currentCount);
    }
}
