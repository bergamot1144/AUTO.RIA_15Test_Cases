package pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VehicleCheckerPage {
    public String getSelectedCity() {
        $("select[name='city']")
                .shouldBe(visible)
                .click();

        return $("select[name='city'] option:checked")
                .shouldBe(visible)
                .getText()
                .trim();
    }

}
