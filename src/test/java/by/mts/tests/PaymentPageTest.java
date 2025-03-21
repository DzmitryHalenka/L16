package by.mts.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import by.mts.pages.Locators;
import by.mts.pages.PaymentPage;

public class PaymentPageTest {
    private WebDriver driver;
    private PaymentPage paymentPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mts.by");

        paymentPage = new PaymentPage(driver);
        paymentPage.acceptCookies();
    }

    @Test
    public void testPayBlockCustomTitle() {
        paymentPage.checkPayBlock();
        paymentPage.checkPayBlockName("Онлайн пополнение\nбез комиссии");
    }

    @Test
    public void checkPaymentLogosTest() {
        paymentPage.checkLogoVisibility(Locators.VISA_LOGO, "Visa");
        paymentPage.checkLogoVisibility(Locators.MASTERCARD_LOGO, "MasterCard");
    }

    @Test
    public void testServiceTypeButton() {
        paymentPage.checkAndClickButton(Locators.SERVICE_TYPE_BUTTON);
    }

    @Test
    public void testFillPaymentForm() {
        paymentPage.checkPayBlock();
        paymentPage.selectService("Услуги связи");

        // Очистка и ввод данных для "Услуги связи"
        paymentPage.clearFields(Locators.PHONE_NUMBER_FIELD_CONNECTION, Locators.AMOUNT_FIELD_CONNECTION, Locators.EMAIL_FIELD_CONNECTION);
        paymentPage.enterData(Locators.PHONE_NUMBER_FIELD_CONNECTION, Locators.AMOUNT_FIELD_CONNECTION, Locators.EMAIL_FIELD_CONNECTION, "297777777", "10", "test@example.com");

        paymentPage.checkAndClickButton(Locators.CONTINUE_BUTTON);
    }

    @Test
    public void testSelectedService() {
        paymentPage.selectService("Услуги связи");
        paymentPage.checkSelectedService("Услуги связи");
    }

    @Test
    public void testEmptyFieldPlaceholders() {
        paymentPage.verifyService("Услуги связи", Locators.SERVICES_BUTTON, "Номер телефона", "Сумма", "E-mail для отправки чека");
        paymentPage.verifyService("Домашний интернет", Locators.SERVICES_BUTTON, "Номер абонента", "Сумма", "E-mail для отправки чека");
        paymentPage.verifyService("Рассрочка", Locators.SERVICES_BUTTON, "Номер счета на 44", "Сумма", "E-mail для отправки чека");
        paymentPage.verifyService("Задолженность", Locators.SERVICES_BUTTON, "Номер счета на 2073", "Сумма", "E-mail для отправки чека");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
