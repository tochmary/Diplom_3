package pages;

import helpers.URL;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

//Страница "Личный Кабинет"
public class AccountPage extends BasePage {

    //Раздел "Профиль"
    private final By profileSection = By.xpath(".//*[text()='Профиль']");
    //значение поля "Имя"
    private final By nameField = By.xpath(".//input[@name='Name' and @disabled]");
    //значение поля "Email"
    private final By emailField = By.xpath(".//input[@name='name' and @disabled]");
    //кнопка "Выход"
    private final By exitButton = By.xpath(".//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    // метод ожидания загрузки страницы: проверили видимость названия раздела "Профиль"
    public void waitForLoadPage() {
        // ждем 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profileSection));
    }

    //Клик по кнопке "Выход"
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }

    //метод получения значение имени
    public String getNameField() {
        return driver.findElement(nameField).getDomAttribute("value");
    }

    //метод получения значение имени
    public String getEmailField() {
        return driver.findElement(emailField).getDomAttribute("value");
    }
}
