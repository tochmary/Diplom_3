import helpers.URL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AccountPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest extends AbstractTest {

    AccountPage objAccountPage;

    @BeforeEach
    public void beforeEach() {
        objAccountPage = new AccountPage(driver);
    }

    @Test
    @DisplayName("Нажатие на логотип \"Stellar Burgers\"")
    public void checkClickLogoStellarBurgers() {
        objAccountPage.clickLogoStellarBurgers();
        assertEquals(URL.getHost() + "/", driver.getCurrentUrl(), "Открылась не главная страница \"Stellar Burgers\"!");
    }

    @Test
    @DisplayName("Нажатие на кнопку \"Конструктор\"")
    public void checkClickConstructorButton() {
        objAccountPage.clickConstructorButton();
        assertEquals(URL.getHost() + "/", driver.getCurrentUrl(), "Открылась не главная страница \"Stellar Burgers\"!");
    }

}
