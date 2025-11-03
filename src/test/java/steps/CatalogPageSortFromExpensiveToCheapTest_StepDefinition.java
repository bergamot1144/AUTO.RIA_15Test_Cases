package steps;

import com.codeborne.selenide.ElementsCollection;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CatalogPage;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.assertTrue;

public class CatalogPageSortFromExpensiveToCheapTest_StepDefinition {
    CatalogPage catalogPage = new CatalogPage();
    Logger logger = Logger.getLogger("Test");

    @When("User click on sort button")
    public void userClickOnSortButton() {
        catalogPage.openSortingMenu();
    }

    @And("Pick {int} sort button")
    public void pickSortButton(int number) {
        catalogPage.selectSorting(number);
    }

    @Then("The user makes sure that each subsequent card is cheaper than the previous one")
    public void theUserMakesSureThatEachSubsequentCardIsCheaperThanThePreviousOne() {
        sleep(1500);

        ElementsCollection allCards = $$(".product-card");
        logger.info("Total cards found: " + allCards.size());
        List<Integer> prices = new ArrayList<>();

        for (int i = 0; i < allCards.size(); i++) {
            if (i == 2) {
                logger.info("Skipping element 3 as requested");
                continue;
            }

            String priceText = allCards.get(i).$(".common-text.titleM.c-green").getText();
            String cleanPrice = priceText.replaceAll("[^\\d]", "");

            if (!cleanPrice.isEmpty()) {
                int price = Integer.parseInt(cleanPrice);
                prices.add(price);
                logger.info("Extracted price from card " + (i + 1) + ": " + price);
            }
        }

        assertTrue(prices.size() >= 2, "Need at least 2 products to compare prices");

        logger.info("All extracted prices: " + prices.toString());

        for (int i = 1; i < prices.size(); i++) {
            int currentPrice = prices.get(i);
            int previousPrice = prices.get(i - 1);
            logger.info("Comparing: Product " + (i + 1) + " (" + currentPrice + "$) vs Product " + i + " ("
                    + previousPrice + "$)");
            assertTrue(
                    currentPrice <= previousPrice,
                    "Product " + (i + 1) + " price (" + currentPrice + "$) should be cheaper than product " + i
                            + " price (" + previousPrice + "$)");
        }
    }
}
