package day3;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Multithreading {

    Browser browser;
    Page page;
    Playwright playwright;
    @BeforeEach
    public void launch(String browserName) {
        BrowserType browserType;

        Playwright playwright = Playwright.create();
        switch(browserName) {
            case "Chrome":
                browserType = playwright.chromium();
                break;
            case "Firefox":
                browserType = playwright.firefox();
                break;
            case "Webkit":
                browserType = playwright.webkit();
                break;
            default:
                throw new illegalArgumentException("Please provide valid browsertype");
        }
browser=browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browse1 = browser.newContext();
        Page browser1 = browse1.newPage();
        browser1.navigate("https://www.saucedemo.com/");
        browser1.fill("#user-name", "standard_user");
        browser1.fill("#password", "secret_sauce");
        assertThat(browser1.locator("#login-button")).isEnabled();
        browser1.click("#login-button");
        System.out.println("the Login Button is Enabled ");


        browse1.close();
        browser1.close();
        playwright.close();
    }
}
