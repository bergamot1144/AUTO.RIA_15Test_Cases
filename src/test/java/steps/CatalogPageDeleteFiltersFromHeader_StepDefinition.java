package steps;

import com.codeborne.selenide.ElementsCollection;
import io.cucumber.java.en.Then;
import pages.CatalogPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.CollectionCondition.sizeLessThanOrEqual;
import static org.testng.Assert.assertFalse;

public class CatalogPageDeleteFiltersFromHeader_StepDefinition {
    CatalogPage catalogPage = new CatalogPage();

    @Then("User click clear all button and verify that all chips are disappear")
    public void userClickClearAllButtonAndVerifyThatAllChipsAreDisappear() {
        sleep(1000);
        catalogPage.clickClearAllButton();
        sleep(3000);
        $("#headerChipsContainer").$$("span").shouldHave(sizeLessThanOrEqual(1));
        boolean areChipsVisible = $("#headerChipsContainer").isDisplayed();

        if (areChipsVisible) {
            ElementsCollection chips = $("#headerChipsContainer").$$("span");
            List<String> allowedStatic = List.of("Очистити все", "Очистить все", "Авто в Україні", "Розмитнені");
            List<String> remaining = chips.texts().stream()
                    .map(String::trim)
                    .filter(t -> !t.isEmpty())
                    .filter(t -> !allowedStatic.contains(t))
                    .toList();
            assertFalse(remaining.size() > 0, "Chips are not cleared. Remaining: " + String.join(", ", remaining));
        }
    }
}
