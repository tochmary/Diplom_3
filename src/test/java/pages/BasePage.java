package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс базовой страницы
abstract public class BasePage {
    protected WebDriver driver;

    //кнопка "Конструктор"
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    //логотип "Stellar Burgers"
    private final By logoStellarBurgers = By.className("AppHeader_header__logo__2D0X2");
    //кнопка "Личный Кабинет"
    private final By accountButton = By.xpath(".//p[text()='Личный Кабинет']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    //Клик по логотипу "Stellar Burgers"
    public void clickLogoStellarBurgers() {
        driver.findElement(logoStellarBurgers).click();
    }

    //Клик по логотипу "Stellar Burgers"
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    //Клик по логотипу "Личный Кабинет"
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

}