package pages;

import helpers.URL;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//главная страница
public class MainPage extends BasePage{

    //заголовок главной страницы
    private final By headerSection = By.xpath(".//section/h1[text()='Соберите бургер']");
    //раздел "Булки"
    private final By bunSection = By.xpath(".//span[text()='Булки']");
    //раздел "Соусы"
    private final By sauceSection = By.xpath(".//span[text()='Соусы']");
    //раздел "Начинки"
    private final By fillingSection = By.xpath(".//span[text()='Начинки']");
    //кнопка "Войти в аккаунт"
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    //метод наличия заголовка
    public boolean isHeaderSection() {
        return driver.findElement(headerSection).isDisplayed();
    }

    //Клик на раздел "Булки"
    public void clickBunSection() {
        driver.findElement(bunSection).click();
    }

    //Клик на раздел "Соусы"
    public void clickSauceSection() {
        driver.findElement(sauceSection).click();
    }

    //Клик на раздел "Начинки"
    public void clickFillingSection() {
        driver.findElement(fillingSection).click();
    }

    //Клик по кнопке "Войти в аккаунт"
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Проверка нахождения на главной страницы")
    public void checkIsMainPage(String currentUrl) {
        assertEquals(URL.getHost() + "/", currentUrl, //driver.getCurrentUrl(),
                "Открыта не главная страница \"Stellar Burgers\"");
        assertTrue(isHeaderSection());
    }

}
