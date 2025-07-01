package pages;

import helpers.URL;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

//Форма "Регистрация"
public class RegisterPage extends BasePage {

    //заголовок страницы "Регистрация"
    private final By registerHeader = By.xpath(
            ".//div[@class='Auth_login__3hAey']/h2[text()='Регистрация']");
    //Поле ввода "Name"
    private final By nameField = By.xpath(
            ".//label[text()='Имя']/parent::div/input[@type='text' and @name='name']");
    //Поле ввода "Email"
    private final By emailField = By.xpath(
            ".//label[text()='Email']/parent::div/input[@type='text' and @name='name']");
    //Поле ввода "Пароль"
    private final By passwordField = By.xpath(".//input[@type='password' and @name='Пароль']");
    //сообщение о некорректном пароле
    private final By passwordMessage = By.xpath(".//*[text()='Некорректный пароль']");

    //кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //кнопка "Войти"
    private final By loginButton = By.xpath(".//a[@href='/login' and text()='Войти']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    // метод ожидания загрузки страницы: проверили видимость заголовка формы регистрации
    public void waitForLoadPage() {
        // ждем 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(registerHeader));
    }

    //метод наличия заголовка
    public boolean isRegisterHeader() {
        return driver.findElement(registerHeader).isDisplayed();
    }

    //метод для заполнения поля "Имя"
    public void setName(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    //метод для заполнения поля "Email"
    public void setEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    //метод для заполнения поля "Пароль"
    public void setPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    //существование сообщения о некорректности поля "* Фамилия"
    public boolean existPasswordMessage() {
        return existMessage(passwordMessage);
    }

    //существование сообщения о некорректности поля
    public boolean existMessage(By field) {
        try {
            driver.findElement(field);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //Клик по кнопке "Зарегистрироваться"
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    //Клик по кнопке "Войти"
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Регистрация пользователя с name={name}, email={email}, password={password}")
    public void registerUser(String name,
                             String email,
                             String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Проверка нахождения на форме регистрации")
    public void checkIsPage() {
        waitForLoadPage();
        assertEquals(URL.getHost() + "/register", driver.getCurrentUrl(),
                "Открыта не форма \"Регистрация\"!");
    }
}
