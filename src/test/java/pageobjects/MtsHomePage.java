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
//    private final By cookieBanner = By.id("bxdynamic_cookies-agreement-pt1_start");
    private final By acceptCookiesBtn = By.cssSelector(".btn.btn_black.cookie__ok");

    public MtsHomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void acceptCookies() {
        try {
//            WebElement cookieBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(cookieBanner));
//            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn));
            WebElement acceptButton = wait.until(ExpectedConditions.visibilityOfElementLocated(acceptCookiesBtn));
            acceptButton.click();
//            wait.until(ExpectedConditions.invisibilityOf(cookieBlock));
            logger.info("Куки успешно приняты");
        } catch (Exception e) {
            logger.warn("Не удалось обработать диалог с cookies {}", e.getMessage());
        }
    }

    public void navigateToPaymentSection() {
        driver.get("https://www.mts.by");
        wait.until(ExpectedConditions.titleContains("МТС"));
    }
}