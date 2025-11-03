package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import support.Config;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private List<TransportData> addedToFavorites = new ArrayList<>();

    public void initMainPage() {
        Config.setupBrowser();
        Selenide.open("https://auto.ria.com/");
        Selenide.webdriver().driver().getWebDriver().manage().window().maximize();
        setupSessionCookies();
    }

    private void setupSessionCookies() {
        sleep(1000);

        try {

            String sessionId = "selenide_session_" + System.currentTimeMillis();
            executeJavaScript(
                    "document.cookie = 'session_id=' + arguments[0] + '; path=/; domain=.auto.ria.com';",
                    sessionId);
            executeJavaScript(
                    "document.cookie = 'user_session=' + arguments[0] + '; path=/; domain=.auto.ria.com';",
                    sessionId);
            org.openqa.selenium.Cookie cookie1 = new org.openqa.selenium.Cookie.Builder("session_id", sessionId)
                    .domain(".auto.ria.com")
                    .path("/")
                    .build();
            org.openqa.selenium.Cookie cookie2 = new org.openqa.selenium.Cookie.Builder("user_session", sessionId)
                    .domain(".auto.ria.com")
                    .path("/")
                    .build();

            try {
                getWebDriver().manage().addCookie(cookie1);
                getWebDriver().manage().addCookie(cookie2);
            } catch (Exception e) {
                System.out.println("Cookie setup via WebDriver failed, using JavaScript: " + e.getMessage());
            }

            sleep(500);
            executeJavaScript("location.reload();");
            sleep(1000);

        } catch (Exception e) {
            System.out.println("Warning: Could not setup session cookies: " + e.getMessage());
        }
    }

    public void FilterButtonTypeOfVehicleClick() {
        $("label[for='sf-1']").shouldBe(visible, enabled).click();
    }

    public void FilterButtonBrandAndModelClick() {
        $("label[for='sf-2']").shouldBe(visible, enabled).click();
    }

    public void FilterButtonYearClick() {
        $("label[for='year']").shouldBe(visible, enabled).click();
    }

    public void FilterButtonPriceClick() {
        $("label[for='sf-3']").shouldBe(visible, enabled).click();
    }

    public void FilterButtonRegionClick() {
        $("label[for='region']").shouldBe(visible, enabled).click();
    }

    public void FilterButtonFuelClick() {
        $("label[for='fuel']").shouldBe(visible, enabled).click();
    }

    public void FilterButtonGearboxClick() {
        $("label[for='gearbox']").shouldBe(visible, enabled).click();
    }

    public void voteForVehicleTypeClick(String vehicleType) {
        $$("label.label-content-wrapper")
                .findBy(text(vehicleType))
                .shouldBe(visible, enabled)
                .click();
        sleep(1000);
    }

    public void voteForBrandAndModelClick(String brand, String model) {
        $$("label.label-content-wrapper")
                .findBy(text(brand))
                .shouldBe(visible, enabled)
                .click();
        sleep(1000);
        $$("label.label-content-wrapper")
                .findBy(text(model))
                .shouldBe(visible, enabled)
                .click();
        sleep(1000);
    }

    public void voteForMinYearClick(int minYear) {
        String selector = String.format("label[for='year.from%d']", minYear);
        $(selector).shouldBe(visible, enabled).click();
        sleep(1000);
    }

    public void voteForMaxYearClick(int maxYear) {
        String selector = String.format("label[for='year.to%d']", maxYear);
        $(selector).shouldBe(visible, enabled).click();
        sleep(1000);
    }

    public void voteForMinPriceClick(int minPrice) {
        $("input.input-common[name='from']").shouldBe(visible, enabled).sendKeys(String.valueOf(minPrice));
        sleep(1000);
    }

    public void voteForMaxPriceClick(int maxPrice) {
        $("input.input-common[name='to']").shouldBe(visible, enabled).sendKeys(String.valueOf(maxPrice));
        sleep(1000);
    }

    public void voteForRegion(String regionName) {
        $$("label.label-content-wrapper")
                .findBy(text(regionName))
                .shouldBe(visible, enabled)
                .click();
        sleep(1000);
    }

    public void voteForFuel(String fuelName) {
        $$("label.label-content-wrapper")
                .findBy(text(fuelName))
                .shouldBe(visible, enabled)
                .click();
        sleep(1000);
    }

    public void voteForGearbox(String gearboxName) {
        $$("label.label-content-wrapper")
                .findBy(text(gearboxName))
                .shouldBe(visible, enabled)
                .click();
        sleep(1000);
    }

    public void ButtonOpenFavouritesClick() {
        $("a[href='/uk/notepad.html']").shouldBe(visible, enabled).click();
    }

    public void RedSearchButtonClick() {
        $("div.button-main.search-form-btn button[type='submit']")
                .shouldBe(visible, enabled)
                .click();
    }

    public void clickHeartButtons(int count) {
        for (int i = 0; i < count; i++) {

            var transportCard = $$("div[id^='Auto'][class*='wrapper-elevation']").get(i);

            String title = transportCard.$("span.titleS").getText();
            String price = transportCard.$("strong.titleM").getText();
            String year = title.split(" ")[title.split(" ").length - 1];
            String location = transportCard.$("span.footnote").getText();

            executeJavaScript("document.body.style.zoom = '0.5';");
            sleep(500);

            var heartButton = $$("div.button-icon button[data-action='notepad']").get(i);

            heartButton.hover();
            sleep(200);


            try {
                executeJavaScript("arguments[0].click();", heartButton);
                sleep(500);
            } catch (Exception e) {
                heartButton.shouldBe(visible, enabled).click();
                sleep(500);
            }

            try {
                SelenideElement svgPath = heartButton.$("svg path");
                String fillValue = svgPath.getAttribute("fill");

                for (int attempt = 0; attempt < 5; attempt++) {
                    fillValue = svgPath.getAttribute("fill");
                    if (fillValue != null && (fillValue.equals("var(--brand)") || fillValue.contains("rgb"))) {
                        break;
                    }
                    sleep(200);
                }
            } catch (Exception e) {
                System.out.println("Could not verify heart button activation: " + e.getMessage());
            }

            addedToFavorites.add(new TransportData(title, price, year, location));

            sleep(1000);
        }
    }

    public List<TransportData> getAddedToFavorites() {
        return addedToFavorites;
    }

    public void openFirstItemAtHomePage() {
        $$("#Recomendations .gap-20.items-list > div")
                .first()
                .click();
    }

    public void tableTypeOfTransportClickAllButton() {
        executeJavaScript("document.body.style.zoom = '0.2';");
        sleep(300);

        SelenideElement allButton = $("a[href='/legkovie-catalog/']");
        sleep(500);
        allButton.shouldBe(visible, enabled).click();

    }

    public void selectBrandFromTable(String brand) {
        System.out.println("Selecting brand: " + brand);
        $("a.item-brands[title='" + brand + "']")
                .shouldBe(visible, enabled)
                .scrollTo()
                .click();
        System.out.println("Brand selected successfully: " + brand);
        sleep(2000);
    }

    public void googlePlayDownloadButtonClick() {
        SelenideElement googlePlayButton = $$("a.btn-app")
                .findBy(attributeMatching("href", ".*play\\.google\\.com.*"));

        googlePlayButton.should(exist)
                .scrollTo()
                .shouldBe(visible, enabled)
                .click();
    }

    public void newCarsHeaderLink() {
        $("a.nav-link[href='/uk/newauto/']").shouldBe(visible).click();
    }

}
