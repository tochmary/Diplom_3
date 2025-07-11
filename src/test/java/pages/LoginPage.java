package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Форма авторизации "Вход"
public class LoginPage extends BasePage {

    //заголовок страницы "Вход"
    private final By loginHeader = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Вход']");
    //Поле ввода "Email"
    private final By emailField = By.xpath(".//input[@name='name']");
    //Поле ввода "Пароль"
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    //кнопка "Войти"
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    //кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath(".//*[text()='Зарегистрироваться']");
    //кнопка "Восстановить пароль"
    private final By recoverButton = By.xpath(".//*[text()='Восстановить пароль']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // метод ожидания загрузки страницы: проверили видимость заголовка страницы "Вход"
    public void waitForLoadPage() {
        // ждем 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
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

    //Клик по кнопке "Войти"
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    //Клик по кнопке "Зарегистрироваться"
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    //Клик по кнопке "Восстановить пароль"
    public void clickRecoverButton() {
        driver.findElement(recoverButton).click();
    }

    @Step("Авторизация пользователя")
    public void loginUser(String email,
                          String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }
}
