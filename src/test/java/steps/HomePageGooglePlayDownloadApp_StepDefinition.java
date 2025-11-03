package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.testng.Assert.assertTrue;
import pages.HomePage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class HomePageGooglePlayDownloadApp_StepDefinition {
    HomePage homePage = new HomePage();

    @When("User click GooglePlay download button")
    public void downloadViaGooglePlayClick() {
        homePage.googlePlayDownloadButtonClick();
        switchTo().window(1);
    }

    @Then("User goes to the download page and makes sure it is correct.")
    public void userGoesToTheDownloadPageAndMakesSureItIsCorrect() {
        SelenideElement autoRiaTitle = $("span.AfwdI[itemprop='name']");

        boolean isVisible = autoRiaTitle.shouldBe(visible).isDisplayed();
        assertTrue(
                isVisible,
                "❌ Елемент 'AUTO.RIA — нові та б/в авто' не відображається на сторінці!");
    }
}
