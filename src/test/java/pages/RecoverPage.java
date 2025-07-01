package pages;

import helpers.URL;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

//Форма "Восстановление пароля"
public class RecoverPage extends BasePage {

    //заголовок формы "Восстановление пароля"
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

    // метод ожидания загрузки страницы: проверили видимость заголовка формы "Восстановление пароля"
    public void waitForLoadPage() {
        // ждем 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(recoverHeader));
    }

    //метод наличия заголовка
    public boolean isRecoverHeader() {
        return driver.findElement(recoverHeader).isDisplayed();
    }

    //метод для заполнения поля "Email"
    public void setEmail(String email) {
        driver.findElement(emailField).clear();
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

    @Step("Проверка нахождения на форме восстановления пароля")
    public void checkIsPage() {
        waitForLoadPage();
        assertEquals(URL.getHost() + "/forgot-password", driver.getCurrentUrl(),
                "Открыта не форма \"Восстановление пароля\"!");
    }
}
