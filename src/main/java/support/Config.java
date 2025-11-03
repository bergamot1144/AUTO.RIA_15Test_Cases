package support;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Config {
    private static boolean allureListenerAdded = false;

    public static void setupBrowser() {
        if (!allureListenerAdded) {
            new AllureSelenide()
                    .screenshots(true)
                    .savePageSource(true);
            allureListenerAdded = true;
        }

        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 30000;
        Configuration.headless = false;

        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();

        profile.setPreference("dom.webdriver.enabled", false);
        profile.setPreference("useAutomationExtension", false);

        profile.setPreference("general.useragent.override",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:121.0) Gecko/20100101 Firefox/121.0");

        profile.setPreference("dom.webnotifications.enabled", false);
        profile.setPreference("dom.push.enabled", false);

        options.setProfile(profile);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        Configuration.browserCapabilities = options;
    }
}
