package pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class NewCarsPage {
    public void realEstateHeaderLink(){
        $("a.item[href*='dom.ria.com'][target='_blank']")
                .shouldHave(text("Нерухомість"))
                .shouldBe(visible)
                .click();
    }
}
