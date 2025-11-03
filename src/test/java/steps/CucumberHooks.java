package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class CucumberHooks {

    @After
    public void tearDown() {
        if (hasWebDriverStarted()) {
            try {
                closeWebDriver();
            } catch (Exception e) {
                System.out.println("Browser already closed: " + e.getMessage());
            }
        }
    }

    @Before
    public void setUp() {

    }
}
