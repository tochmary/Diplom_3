package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class URL {
    private final static String HOST_TEST = "https://stellarburgers.nomoreparties.site";

    public static String getHost() {

        String URL = getProperty("hostTest");

        if (URL != null) {
            return URL;
        } else {
            return HOST_TEST;
        }
    }

    private static String getProperty(String name) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");

            if (inputStream == null) {
                System.out.println("Не удалось вычитать конфиг");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String URL = properties.getProperty(name);
        return URL;
    }
}
