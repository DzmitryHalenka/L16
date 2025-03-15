package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MtsHomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(MtsHomePage.class);

    // Локаторы
    private final By cookieBanner = By.cssSelector("div.cookie.show");
    private final By acceptCookiesBtn = By.id("cookie-agree");

    public MtsHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void acceptCookies() {
        try {
            WebElement cookieBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(cookieBanner));
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn));

            acceptButton.click();
            wait.until(ExpectedConditions.invisibilityOf(cookieBlock));
            logger.info("Cookies accepted successfully");
        } catch (Exception e) {
            logger.warn("Cookie dialog handling failed: {}", e.getMessage());
        }
    }

    public void navigateToPaymentSection() {
        driver.get("https://www.mts.by");
        wait.until(ExpectedConditions.titleContains("МТС"));
    }
}