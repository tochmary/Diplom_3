package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {
    public WebDriver getWebDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            case "yandex":
//                System.setProperty("webdriver.opera.driver", "C:\\Users\\User\\IdeaProjects\\testselenium\\drivers\\operadriver.exe");
//                OperaOptions options = new OperaOptions();
//                options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
//                WebDriver driver = new OperaDriver(options);

//                from selenium import webdriver
//                    options = webdriver.ChromeOptions()
//
//                binary_yandex_driver_file = 'yandexdriver.exe' # path to YandexDriver
//
//                    driver = webdriver.Chrome(binary_yandex_driver_file, options=options)
//                driver.get('https://yandex.ru')
//                driver.quit();

                return new ChromeDriver(); //TODO код для его драйвера не написан
            default:
                return new ChromeDriver();
        }
    }
}
