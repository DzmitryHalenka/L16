package by.mts.pages;

import org.openqa.selenium.By;


public class Locators {

    // Cookie Block
    public static final By COOKIE_BLOCK = By.xpath("/html/body/div[6]/main/div/div[2]");
    public static final By ACCEPT_COOKIES_BTN = By.cssSelector(".btn.btn_black.cookie__ok");

    // Pay Block
    public static final By PAY_BLOCK_NAME = By.xpath("/html/body/div[6]/main/div/div[4]/div[1]/div/div/div[2]/section/div/h2");

    // Services
    public static final By SERVICES_BUTTON = By.cssSelector("button.select__header");
    public static final By SERVICE_OPTION = By.xpath("//ul[@class='select__list']//p[@class='select__option']");
    public static final By SELECTED_SERVICE_TEXT = By.cssSelector("span.select__now"); // Локатор для отображаемой выбранной услуги
    public static final By SERVICE_TYPE_BUTTON = By.linkText("Подробнее о сервисе");

    // Service-specific Fields (Phone, Amount, Email)
    // Услуги связи
    public static final By PHONE_NUMBER_FIELD_CONNECTION = By.id("connection-phone");
    public static final By AMOUNT_FIELD_CONNECTION = By.id("connection-sum");
    public static final By EMAIL_FIELD_CONNECTION = By.id("connection-email");

    // Домашний интернет
    public static final By PHONE_NUMBER_FIELD_INTERNET = By.id("internet-phone");
    public static final By AMOUNT_FIELD_INTERNET = By.id("internet-sum");
    public static final By EMAIL_FIELD_INTERNET = By.id("internet-email");

    // Рассрочка
    public static final By PHONE_NUMBER_FIELD_INSTALMENT = By.id("score-instalment");
    public static final By AMOUNT_FIELD_INSTALMENT = By.id("instalment-sum");
    public static final By EMAIL_FIELD_INSTALMENT = By.id("instalment-email");

    // Задолженность
    public static final By PHONE_NUMBER_FIELD_ARREARS = By.id("score-arrears");
    public static final By AMOUNT_FIELD_ARREARS = By.id("arrears-sum");
    public static final By EMAIL_FIELD_ARREARS = By.id("arrears-email");

    // Continue Button
    public static final By CONTINUE_BUTTON = By.cssSelector("#pay-connection button");

    // Payment Logos
    public static final By VISA_LOGO = By.cssSelector("img[alt='Visa']");
    public static final By MASTERCARD_LOGO = By.cssSelector("img[alt='MasterCard']");

    // Details Link
    public static final By DETAILS_LINK = By.cssSelector("a[href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']");
}


//public class Locators {
//    // Блок cookies
//    public static final By COOKIE_BLOCK = By.xpath("/html/body/div[6]/main/div/div[2]");
//    public static final By ACCEPT_COOKIES_BTN = By.cssSelector(".btn.btn_black.cookie__ok");
//
//    // Блок с платежной информацией
//    public static final By PAY_BLOCK_NAME = By.xpath("/html/body/div[6]/main/div/div[4]/div[1]/div/div/div[2]/section/div/h2");
//
//    // Кнопка выбора услуги
//    public static final By SERVICES_BUTTON = By.cssSelector("button.select__header");
//
//    // Опции услуги
//    public static final By SERVICE_OPTION = By.xpath("//ul[@class='select__list']//p[@class='select__option']");
//
//    // Текст выбранной услуги
//    public static final By SELECTED_SERVICE_TEXT = By.cssSelector("span.select__now");
//
//    // Кнопка 'Подробнее о сервисе'
//    public static final By SERVICE_TYPE_BUTTON = By.linkText("Подробнее о сервисе");
//
//    // Поля ввода для услуги "Услуги связи"
//    public static final By PHONE_NUMBER_FIELD = By.id("connection-phone");
//    public static final By AMOUNT_FIELD = By.id("connection-sum");
//    public static final By EMAIL_FIELD = By.id("connection-email");
//
//    // Кнопка продолжения
//    public static final By CONTINUE_BUTTON = By.cssSelector("#pay-connection button");
//
//    // Логотипы
//    public static final By VISA_LOGO = By.cssSelector("img[alt='Visa']");
//    public static final By MASTERCARD_LOGO = By.cssSelector("img[alt='MasterCard']");
//
//    // Ссылка на страницу помощи
//    public static final By DETAILS_LINK = By.cssSelector("a[href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']");
//}
