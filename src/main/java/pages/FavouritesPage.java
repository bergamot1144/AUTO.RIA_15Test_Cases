package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FavouritesPage {
    private final ElementsCollection transportCards = $$("div.boxed.ticket-info");

    public boolean containsTransport(TransportData transportData) {
        String expectedTitle = transportData.getTitle().trim();
        String expectedPrice = transportData.getPrice().trim();
        String expectedLocation = transportData.getLocation().trim();

        System.out.println("=== Searching for transport in favourites ===");
        System.out.println("Expected: title='" + expectedTitle + "', price='" + expectedPrice + "', location='"
                + expectedLocation + "'");

        boolean found = transportCards.asFixedIterable().stream().anyMatch(card -> {
            try {
                String title = card.$("a.size18").getText().trim();
                String price = card.$("span.green.size22.bold").getText().trim();
                String location = card.$("li.item-char").getText().trim();

                System.out
                        .println("Checking: title='" + title + "', price='" + price + "', location='" + location + "'");

                boolean titleMatches = title.equals(expectedTitle);
                if (!titleMatches && expectedTitle.split(" ").length > 1) {
                    String[] expectedWords = expectedTitle.split(" ");
                    titleMatches = title.contains(expectedWords[0]) &&
                            title.contains(expectedWords[expectedWords.length - 1]);
                }

                boolean priceMatches = price.equals(expectedPrice);
                if (!priceMatches) {
                    priceMatches = price.replaceAll("\\s", "").equals(expectedPrice.replaceAll("\\s", ""));
                }

                boolean locationMatches = location.contains(expectedLocation) ||
                        expectedLocation.contains(location) ||
                        location.equals(expectedLocation);

                if (titleMatches && priceMatches && locationMatches) {
                    System.out.println("✓ MATCH FOUND!");
                    return true;
                } else {
                    System.out.println("✗ No match: title=" + titleMatches + ", price=" + priceMatches + ", location="
                            + locationMatches);
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Error comparing transport data: " + e.getMessage());
                return false;
            }
        });

        if (!found) {
            System.out.println("=== Transport NOT FOUND in favourites ===");
        }

        return found;
    }

    public int getFavouritesCountFromHeader() {
        String headerText = $("h1.head-notepad span#advertisementsCounter").getText();
        return Integer.parseInt(headerText.replaceAll("\\D+", ""));
    }

    public boolean isFavouritesPageEmpty() {
        return getFavouritesCountFromHeader() == 0;
    }

    public void clickBlueSearchButton() {
        $("#SearchAction")
                .shouldBe(visible, enabled)
                .click();
    }
}
