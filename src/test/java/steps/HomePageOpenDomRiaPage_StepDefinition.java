package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.testng.Assert.assertTrue;
import pages.HomePage;
import pages.NewCarsPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePageOpenDomRiaPage_StepDefinition {
    HomePage homePage = new HomePage();
    NewCarsPage newCarsPage = new NewCarsPage();

    @When("User click new cars button at header and redirect to the new page")
    public void newCarsButton() {
        homePage.newCarsHeaderLink();
        sleep(2000);
    }

    @And("User click RealEstate button at header and redirect to Dom.Ria website")
    public void openAutoRiaWebsite() {
        newCarsPage.realEstateHeaderLink();
        switchTo().window(1);
    }

    @Then("User makes sure it is correct.")
    public void userGoesToTheDomRiaWebsiteAndMakesSureItIsCorrect() {
        SelenideElement domRiaElement = $("#custom-select-1 .overflowed.bold-500");
        boolean isVisible = domRiaElement.shouldBe(visible).isDisplayed();
        assertTrue(
                isVisible,
                "The element is not displayed on the page!");
    }

}
