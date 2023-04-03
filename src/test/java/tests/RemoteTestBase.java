package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.RegistrationPage;
import helpers.Attach;

import java.util.Map;

public class RemoteTestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    static String baseUrlSite = System.getProperty("baseUrlSite", "https://demoqa.com");
    static String browserNameForTest = System.getProperty("browserNameForTest", "chrome");
    static String browserVersionForTest = System.getProperty("browserVersionForTest", "100.0");
    static String browserSizeForTest = System.getProperty("browserSizeForTest", "1920x1080");
    static String remoteUrlForTest = System.getProperty("remoteUrlForTest", "selenoid.autotests.cloud");

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = baseUrlSite;
        Configuration.browser = browserNameForTest;
        Configuration.browserVersion = browserVersionForTest;
        Configuration.browserSize = browserSizeForTest;
        Configuration.remote = "https://user1:1234@" + remoteUrlForTest + "/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}