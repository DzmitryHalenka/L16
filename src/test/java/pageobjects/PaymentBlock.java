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
    private final By serviceDropdown = By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button");
    private final By serviceOptions = By.cssSelector("li.pay-section__select-item");
    private final By phoneInput = By.id("connection-phone");
    private final By amountInput = By.id("connection-sum");
    private final By emailInput = By.cssSelector("input#connection-email");
    private final By continueButton = By.cssSelector("#pay-connection button");

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
            logger.info("Выбранный сервис {}", serviceName);
        } catch (TimeoutException e) {
            logger.error("Тайм-аут выбора сервиса {}", e.getMessage());
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
        assertEquals(expected, field.getDomAttribute("placeholder"),
                "Несоответствие placeholder для " + locator.toString());
    }

    public void verifyContinueButtonState(boolean isActive) {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton));
        assertEquals(isActive, button.isEnabled(),
                "Несоответствие состояния кнопки 'Продолжить'");
    }
}
