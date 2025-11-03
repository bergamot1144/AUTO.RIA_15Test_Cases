package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {
    private final SelenideElement chipsContainer = $("#headerChipsContainer");

    public boolean isChipsContainerVisible() {
        return chipsContainer.isDisplayed();
    }

    public void waitForCatalogPageLoad() {
        sleep(5000);
    }

    public String getChipsContainerText() {
        return chipsContainer.shouldBe(visible).getText();
    }

    public boolean containsFilterChip(String filterValue) {
        return chipsContainer.shouldBe(visible).getText().contains(filterValue);
    }

    public boolean containsYearRange(String minYear, String maxYear) {
        String yearRange = "від " + minYear + " р. - до " + maxYear + " р.";
        return chipsContainer.shouldBe(visible).getText().contains(yearRange);
    }

    public boolean containsPriceRange(String minPrice, String maxPrice) {
        String formattedMinPrice = formatPrice(minPrice);
        String formattedMaxPrice = formatPrice(maxPrice);
        String priceRange = "від " + formattedMinPrice + " $ - до " + formattedMaxPrice + " $";
        return chipsContainer.shouldBe(visible).getText().contains(priceRange);
    }

    private String formatPrice(String price) {

        if (price.length() > 3) {
            return price.substring(0, price.length() - 3) + " " + price.substring(price.length() - 3);
        }
        return price;
    }

    public void clickClearAllButton() {
        SelenideElement clearButton = $$("button[data-action='changeSearchForm']")
                .findBy(text("Очистити все"));

        clearButton.should(exist)
                .scrollIntoView("{block:'center'}")
                .shouldBe(visible, enabled)
                .click();
    }

    public void openSortingMenu() {
        $(".common-sorting .button-ghost button")
                .shouldBe(visible, enabled)
                .click();
    }

    public void selectSorting(int number) {
        ElementsCollection sortingOptions = $$(".common-sorting .label-content-wrapper");
        int index = number - 1;
        sortingOptions.get(index)
                .shouldBe(visible, enabled)
                .click();
        sleep(5000); // Increased wait time for page to re-sort
    }

    public void visitOldWebsiteVersion() {
        $$("button").findBy(text("Повернутися до попередньої версії"))
                .shouldBe(visible, enabled)
                .click();
    }

    public void redButtonSubmitOpenOldWebsiteVersion() {
        $$("button").findBy(text("Перейти"))
                .shouldBe(visible, enabled)
                .click();
    }

    public boolean isNewWebsiteVersionButtonVisible() {
        return $$("button").findBy(text(" Перейти до нової версії ")).isDisplayed();
    }

    public SelenideElement priceFromField = $("input[name='price[1]']");
    public SelenideElement priceToField = $("input[name='price[2]']");

    public void setPriceFrom(String value) {
        priceFromField.shouldBe(visible, enabled).setValue(value);
    }

    public void setPriceTo(String value) {
        priceToField.shouldBe(visible, enabled).setValue(value).sendKeys(Keys.ENTER);
        sleep(1000);
    }

    public void selectFuelType(String fuelType) {
        switch (fuelType.toLowerCase()) {
            case "бензин":
                $("label[for='fuelOne']").shouldBe(visible, enabled).click();
                break;
            case "дизель":
                $("label[for='fuelTwo']").shouldBe(visible, enabled).click();
                break;
            case "електро":
                $("label[for='fuelSix']").shouldBe(visible, enabled).click();
                break;
            default:
                throw new IllegalArgumentException("Unknown fuel type:" + fuelType);
        }
    }

    public boolean verifyAllTransportMatchesBrand() {

        String selectedBrand = getBrandFromFirstAdvertisement();
        if (selectedBrand == null || selectedBrand.isEmpty()) {
            System.out.println("Failed to determine brand from advertisements");
            return false;
        }

        System.out.println("Checking advertisements for brand match: " + selectedBrand);

        ElementsCollection transportCards = $$("section.ticket-item");

        if (transportCards.isEmpty()) {
            System.out.println("No advertisements found with main selector section.ticket-item, trying alternative");
            String[] alternativeCardSelectors = {
                    "div[id^='Auto'][class*='wrapper-elevation']",
                    "div[class*='auto-card']",
                    "div[class*='car-card']",
                    "div[class*='vehicle-card']",
                    "div[class*='listing']",
                    ".auto-item",
                    ".car-item",
                    ".vehicle-item"
            };

            for (String selector : alternativeCardSelectors) {
                transportCards = $$(selector);
                if (!transportCards.isEmpty()) {
                    System.out.println("Found advertisements with selector: " + selector);
                    break;
                }
            }

            if (transportCards.isEmpty()) {
                System.out.println("No advertisements found on page");
                return false;
            }
        }

        System.out.println("Found advertisements: " + transportCards.size());

        for (int i = 0; i < transportCards.size(); i++) {
            SelenideElement card = transportCards.get(i);
            String brandName = "";

            String dataMarkName = card.getAttribute("data-mark-name");
            if (dataMarkName != null && !dataMarkName.isEmpty()) {
                brandName = dataMarkName;
                System.out.println("Advertisement " + (i + 1) + " - brand from data-mark-name: " + brandName);
            } else {
                brandName = card.$("span.blue.bold").getText();
                System.out.println("Advertisement " + (i + 1) + " - brand found in span.blue.bold: " + brandName);
            }

            if (!brandName.toLowerCase().contains(selectedBrand.toLowerCase())) {
                System.out.println("ERROR: Advertisement " + (i + 1) + " does not contain brand " + selectedBrand
                        + ". Found brand: " + brandName);
                return false;
            }

            System.out.println("✓ Advertisement " + (i + 1) + " matches brand " + selectedBrand + " (found: "
                    + brandName + ")");
        }

        System.out.println("All advertisements match selected brand: " + selectedBrand);
        return true;
    }

    private String getBrandFromFirstAdvertisement() {
        System.out.println("Extracting brand from first advertisement on page...");

        ElementsCollection transportCards = $$("section.ticket-item");

        if (transportCards.isEmpty()) {
            System.out.println("No advertisements found on page");
            return null;
        }

        SelenideElement firstCard = transportCards.get(0);
        String dataMarkName = firstCard.getAttribute("data-mark-name");

        if (dataMarkName != null && !dataMarkName.isEmpty()) {
            System.out.println("Brand from first advertisement (data-mark-name): " + dataMarkName);
            return dataMarkName.toUpperCase();
        }

        String brandFromSpan = firstCard.$("span.blue.bold").getText();
        if (brandFromSpan != null && !brandFromSpan.isEmpty()) {
            String[] words = brandFromSpan.split(" ");
            if (words.length > 0) {
                System.out.println("Brand from first advertisement (span.blue.bold): " + words[0]);
                return words[0].toUpperCase();
            }
        }

        System.out.println("Failed to extract brand from first advertisement");
        return null;
    }
}
