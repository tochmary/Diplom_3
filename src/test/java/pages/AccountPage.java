package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//Страница "Личный Кабинет"
public class AccountPage extends BasePage {

    //Раздел "Профиль"
    private final By profileSection = By.xpath(".//*[@href='/account/profile' and text()='Профиль']");
    //кнопка "Выход"
    private final By exitButton = By.xpath(".//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    //метод наличия заголовка
    public boolean isProfileSection() {
        return driver.findElement(profileSection).isDisplayed();
    }

    //Клик по кнопке "Выход"
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }
}
