package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//главная страница
public class MainPage extends BasePage {

    //заголовок главной страницы
    private final By headerSection = By.xpath(".//section/h1[text()='Соберите бургер']");
    //раздел "Булки"
    private final By bunSection = By.xpath(".//span[text()='Булки']");
    //раздел "Соусы"
    private final By sauceSection = By.xpath(".//span[text()='Соусы']");
    //раздел "Начинки"
    private final By fillingSection = By.xpath(".//span[text()='Начинки']");
    //выбранный раздел
    private final By selectedSection = By.xpath("//div[contains(@class,'tab_tab_type_current__2BEPc')]/span");
    //кнопка "Войти в аккаунт"
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // метод ожидания загрузки страницы: проверили видимость заголовка главной страницы
    public void waitForLoadPage() {
        // ждем 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(headerSection));
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

    //Метод получения выбранного раздела
    public String getSelectedSection() {
        return driver.findElement(selectedSection).getText();
    }

    //Клик по кнопке "Войти в аккаунт"
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
