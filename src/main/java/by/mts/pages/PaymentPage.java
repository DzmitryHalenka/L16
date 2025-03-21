package by.mts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class PaymentPage extends BasePage {

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public void acceptCookies() {
        acceptCookiesIfPresent(Locators.COOKIE_BLOCK, Locators.ACCEPT_COOKIES_BTN);
    }

    public void checkPayBlock() {
        WebElement payBlock = find(Locators.PAY_BLOCK_NAME);
        Assert.assertNotNull(payBlock, "Блок с платежной информацией не найден.");
    }

    public void checkPayBlockName(String expectedTitle) {
        WebElement payBlockTitle = find(Locators.PAY_BLOCK_NAME);
        String actualTitle = payBlockTitle.getText();
        Assert.assertEquals(actualTitle, expectedTitle, "Название блока не соответствует ожидаемому");
    }

    public void selectService(String serviceName) {
        try {
            click(Locators.SERVICES_BUTTON);
            By selectedServiceLocator = By.xpath("//ul[@class='select__list']//p[@class='select__option' and text()='" + serviceName + "']");
            click(selectedServiceLocator);
            System.out.println("Услуга \"" + serviceName + "\" успешно выбрана.");
        } catch (Exception e) {
            System.out.println("Ошибка при выборе услуги: " + e.getMessage());
            Assert.fail("Не удалось выбрать услугу: " + serviceName);
        }
    }

    public void checkSelectedService(String expectedServiceName) {
        WebElement selectedService = find(Locators.SELECTED_SERVICE_TEXT);
        String actualServiceName = selectedService.getText();
        System.out.println("Фактическое название выбранной услуги: " + actualServiceName);
        Assert.assertEquals(actualServiceName, expectedServiceName, "Ошибка: неверно выбрана услуга");
    }

    // Универсальный метод для очистки полей
    public void clearFields(By phoneFieldLocator, By amountFieldLocator, By emailFieldLocator) {
        try {
            setText(phoneFieldLocator, "");
            setText(amountFieldLocator, "");
            setText(emailFieldLocator, "");
            System.out.println("Поля успешно очищены.");
        } catch (Exception e) {
            System.out.println("Ошибка при очистке полей: " + e.getMessage());
        }
    }

    // Универсальный метод для ввода данных
    public void enterData(By phoneFieldLocator, By amountFieldLocator, By emailFieldLocator, String phoneNumber, String amount, String email) {
        try {
            setText(phoneFieldLocator, phoneNumber);
            setText(amountFieldLocator, amount);
            setText(emailFieldLocator, email);
            System.out.println("Данные успешно введены: номер телефона - " + phoneNumber + ", сумма - " + amount + ", email - " + email);
        } catch (Exception e) {
            System.out.println("Ошибка при вводе данных: " + e.getMessage());
        }
    }

    public void checkAndClickButton(By locator) {
        try {
            Assert.assertTrue(isElementVisible(locator), "Кнопка не видна");
            click(locator);
            System.out.println("Кнопка по локатору " + locator + " успешно кликнута.");
        } catch (Exception e) {
            System.out.println("Ошибка при клике по кнопке: " + e.getMessage());
        }
    }

    public void checkLogoVisibility(By locator, String logoName) {
        boolean isVisible = isElementVisible(locator);
        Assert.assertTrue(isVisible, logoName + " логотип не виден.");
    }

    public void verifyService(String serviceName, By serviceButtonLocator, String phoneFieldPlaceholder, String amountFieldPlaceholder, String emailFieldPlaceholder) {
        // Клик на кнопку выбора услуги
        selectService(serviceName);

        // Проверяем, что отображается правильное название услуги
        checkSelectedService(serviceName);

        // Проверяем плейсхолдеры для полей в зависимости от услуги
        switch (serviceName) {
            case "Услуги связи":
                checkPlaceholder(Locators.PHONE_NUMBER_FIELD_CONNECTION, "Номер телефона"); // Плейсхолдер для услуги "Услуги связи"
                checkPlaceholder(Locators.AMOUNT_FIELD_CONNECTION, amountFieldPlaceholder);
                checkPlaceholder(Locators.EMAIL_FIELD_CONNECTION, emailFieldPlaceholder);
                break;
            case "Домашний интернет":
                checkPlaceholder(Locators.PHONE_NUMBER_FIELD_INTERNET, "Номер абонента"); // Плейсхолдер для услуги "Домашний интернет"
                checkPlaceholder(Locators.AMOUNT_FIELD_INTERNET, amountFieldPlaceholder);
                checkPlaceholder(Locators.EMAIL_FIELD_INTERNET, emailFieldPlaceholder);
                break;
            case "Рассрочка":
                checkPlaceholder(Locators.PHONE_NUMBER_FIELD_INSTALMENT, "Номер счета на 44");
                checkPlaceholder(Locators.AMOUNT_FIELD_INSTALMENT, amountFieldPlaceholder);
                checkPlaceholder(Locators.EMAIL_FIELD_INSTALMENT, emailFieldPlaceholder);
                break;
            case "Задолженность":
                checkPlaceholder(Locators.PHONE_NUMBER_FIELD_ARREARS, "Номер счета на 2073");
                checkPlaceholder(Locators.AMOUNT_FIELD_ARREARS, amountFieldPlaceholder);
                checkPlaceholder(Locators.EMAIL_FIELD_ARREARS, emailFieldPlaceholder);
                break;
            default:
                throw new IllegalArgumentException("Unknown service: " + serviceName);
        }
    }

    private void checkPlaceholder(By locator, String expectedPlaceholder) {
        WebElement field = find(locator);
        Assert.assertNotNull(field, "Поле не найдено: " + locator);
        String actualPlaceholder = (String) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].getAttribute('placeholder');", field);
        Assert.assertNotNull(actualPlaceholder, "Плейсхолдер отсутствует у поля: " + locator);
        Assert.assertEquals(actualPlaceholder, expectedPlaceholder, "Ошибка в плейсхолдере для " + locator);
        System.out.println("Плейсхолдер для поля " + locator + " проверен успешно: " + actualPlaceholder);
    }
}
