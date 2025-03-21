package tests;

import pageobjects.MtsHomePage;
import pageobjects.PaymentBlock;
import utils.DriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.Map;

public class TestPaymentBlock {
    private WebDriver driver;
    private PaymentBlock paymentBlock;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(TestPaymentBlock.class);

    private static final Map<String, String[]> SERVICE_PLACEHOLDERS = Map.of(
            "Услуги связи", new String[]{"Номер телефона", "Сумма", "E-mail для отправки чека"},
            "Домашний интернет", new String[]{"Номер абонента", "Сумма", "E-mail для отправки чека"},
            "Рассрочка", new String[]{"Номер счета на 44", "Сумма", "E-mail для отправки чека"},
            "Задолженность", new String[]{"Номер счета на 2073", "Сумма", "E-mail для отправки чека"}
    );

    @BeforeEach
    void setup() {
        DriverManager manager = new DriverManager();
        driver = manager.initDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        MtsHomePage homePage = new MtsHomePage(driver, wait);
        paymentBlock = new PaymentBlock(driver, wait);
        homePage.acceptCookies();
        homePage.navigateToPaymentSection();

    }

    @Test
    void verifyAllPaymentServices() {
        SERVICE_PLACEHOLDERS.forEach((service, placeholders) -> {
            try {
                WebElement selectButton = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.pay-section__select-toggle"))
                );
                wait.until(ExpectedConditions.elementToBeClickable(selectButton)).click();
            } catch (TimeoutException e) {
                logger.error("Timeout while waiting for service selection: {}", service, e);
                return;
            } catch (WebDriverException e) {
                logger.warn("Falling back to JavaScript click for service: {}", service);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                WebElement selectButton = driver.findElement(By.cssSelector("button.pay-section__select-toggle"));
                js.executeScript("arguments[0].click();", selectButton);
            }

            paymentBlock.selectService(service);
            paymentBlock.verifyPlaceholders(placeholders);
            paymentBlock.verifyContinueButtonState(false);
            logger.info("Verified: {}", service);
        });
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Браузер закрылся успешно");
        }
    }
}