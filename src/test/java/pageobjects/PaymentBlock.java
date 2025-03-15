package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentBlock {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(PaymentBlock.class);

    // Локаторы
    private final By serviceDropdown = By.cssSelector("button.pay-section__select-toggle");
    private final By serviceOptions = By.cssSelector("li.pay-section__select-item");
    private final By phoneInput = By.cssSelector("input#connection-phone");
    private final By amountInput = By.cssSelector("input#connection-sum");
    private final By emailInput = By.cssSelector("input#connection-email");
    private final By continueButton = By.cssSelector("button.pay-form__submit");

    public PaymentBlock(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void selectService(String serviceName) {
        try {
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(serviceDropdown));
            scrollAndClick(dropdown);

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(String.format("//p[contains(., '%s')]", serviceName))
            ));
            scrollAndClick(option);
            logger.info("Selected service: {}", serviceName);
        } catch (TimeoutException e) {
            logger.error("Service selection timeout: {}", e.getMessage());
            throw e;
        }
    }

    private void scrollAndClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void verifyPlaceholders(String[] expected) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));

        assertPlaceholder(phoneInput, expected[0]);
        assertPlaceholder(amountInput, expected[1]);
        assertPlaceholder(emailInput, expected[2]);
    }

    private void assertPlaceholder(By locator, String expected) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        assertEquals(expected, field.getAttribute("placeholder"),
                "Placeholder mismatch for " + locator.toString());
    }

    public void verifyContinueButtonState(boolean isActive) {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton));
        assertEquals(isActive, button.isEnabled(),
                "Continue button state mismatch");
    }
}