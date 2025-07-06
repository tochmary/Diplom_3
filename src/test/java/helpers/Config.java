package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final static Properties properties = new Properties();
    private final static String HOST_TEST = "https://stellarburgers.nomoreparties.site";

    static {
        try {
            InputStream inputStream = Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream("config.properties");
            if (inputStream == null) {
                System.out.println("Не удалось вычитать конфиг");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getHost() {
        return properties.getProperty("hostTest", HOST_TEST);
    }

    public static WebDriver getWebDriver() {
        String browserName = properties.getProperty("browser");
        switch (browserName) {
            case "firefox":
                return new FirefoxDriver();
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
                return new ChromeDriver(options);
            default:
                return new ChromeDriver();
        }
    }
}
