package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//Форма "Восстановление пароля"
public class RecoverPage extends BasePage {

    //заголовок страницы "Восстановление пароля"
    private final By recoverHeader = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Восстановление пароля']");
    //Поле ввода "Email"
    private final By emailField = By.xpath(".//input[@type='text' and @name='name']");
    //кнопка "Восстановить"
    private final By recoverButton = By.xpath(".//button[text()='Восстановить']");
    //кнопка "Войти"
    private final By loginButton = By.xpath(".//a[@href='/login' and text()='Войти']");

    public RecoverPage(WebDriver driver) {
        super(driver);
    }

    //метод наличия заголовка
    public boolean isRecoverHeader() {
        return driver.findElement(recoverHeader).isDisplayed();
    }

    //метод для заполнения поля "Email"
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    //Клик по кнопке "Восстановить"
    public void clickRecoverButton() {
        driver.findElement(recoverButton).click();
    }

    //Клик по кнопке "Войти"
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
