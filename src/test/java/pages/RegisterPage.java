package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

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

    //метод наличия заголовка
    public boolean isRegisterHeader() {
        return driver.findElement(registerHeader).isDisplayed();
    }

    //метод для заполнения поля "Имя"
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    //метод для заполнения поля "Email"
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    //метод для заполнения поля "Пароль"
    public void setPassword(String password) {
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

}
