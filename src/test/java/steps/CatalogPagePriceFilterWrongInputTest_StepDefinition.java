package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.testng.Assert.assertTrue;
import pages.CatalogPage;

import static com.codeborne.selenide.Selenide.sleep;

public class CatalogPagePriceFilterWrongInputTest_StepDefinition {
    CatalogPage catalogPage = new CatalogPage();

    @When("User enters {string} in the From field")
    public void userEntersInTheField(String from) {
        catalogPage.setPriceFrom(from);
        catalogPage.priceToField.click();
        sleep(1000);
    }

    @And("User enters {string} in the To field")
    public void userEntersInTheToField(String to) {
        catalogPage.setPriceTo(to);
    }

    @Then("User verify that From field should be less than To field")
    public void userVerifyThatFieldShouldBeLessThanField() {
        sleep(500);

        String valueFrom = catalogPage.priceFromField.getValue();
        String valueTo = catalogPage.priceToField.getValue();

        System.out.println("Original From value: '" + valueFrom + "'");
        System.out.println("Original To value: '" + valueTo + "'");

        String cleanFrom = valueFrom.replaceAll("[\\s\\u00A0]+", "");
        String cleanTo = valueTo.replaceAll("[\\s\\u00A0]+", "");

        System.out.println("Cleaned From value: '" + cleanFrom + "'");
        System.out.println("Cleaned To value: '" + cleanTo + "'");

        int fromValue = Integer.parseInt(cleanFrom);
        int toValue = Integer.parseInt(cleanTo);

        assertTrue(fromValue < toValue,
                "From field (" + fromValue + ") should be less than To field (" + toValue + ")");

    }

}
