package tests;

import pageobjects.MtsHomePage;
import pageobjects.PaymentBlock;
import utils.DriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.Map;

public class TestPaymentBlock {
    private WebDriver driver;
    private WebDriverWait wait;
    private MtsHomePage homePage;
    private PaymentBlock paymentBlock;
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
        homePage = new MtsHomePage(driver, wait);
        paymentBlock = new PaymentBlock(driver, wait);

        homePage.navigateToPaymentSection();
        homePage.acceptCookies();
    }

    @Test
    void verifyAllPaymentServices() {
        SERVICE_PLACEHOLDERS.forEach((service, placeholders) -> {
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
            logger.info("Browser closed successfully");
        }
    }
}