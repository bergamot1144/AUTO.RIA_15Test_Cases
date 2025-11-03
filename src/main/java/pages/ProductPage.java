package pages;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    public void heartButtonClick() {
        $("button[data-action='notepad']")
                .shouldBe(visible, enabled)
                .click();
    }

    public void checkVehicleHistory() {
        $("#sideCheckAuto")
                .shouldBe(visible, enabled)
                .click();
    }

    public String getGeoFromProductPage() {
        String fullAddress = $("#basicInfoTableMainInfoGeo")
                .shouldBe(visible)
                .getText()
                .trim();

        String[] parts = fullAddress.split(",");
        if (parts.length >= 3) {
            return parts[2].trim();
        }
        return fullAddress;
    }

}
