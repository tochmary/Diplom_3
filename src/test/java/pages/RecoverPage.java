package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Форма "Восстановление пароля"
public class RecoverPage extends BasePage {

    //заголовок формы "Восстановление пароля"
    private final By recoverHeader = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Восстановление пароля']");
    //кнопка "Войти"
    private final By loginButton = By.xpath(".//*[text()='Войти']");

    public RecoverPage(WebDriver driver) {
        super(driver);
    }

    // метод ожидания загрузки страницы: проверили видимость заголовка формы "Восстановление пароля"
    public void waitForLoadPage() {
        // ждем 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(recoverHeader));
    }

    //Клик по кнопке "Войти"
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
