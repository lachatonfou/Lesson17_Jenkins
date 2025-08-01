package ui;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

@Story("UI POM tests")
@Tag("ui")
public class UiPomTests extends BaseTest {
    @Test
    void loginPomTest() {
        LoginPage loginPage = new LoginPage(driver, longWait);
        loginPage.login();
        longWait.until(ExpectedConditions.urlContains("login-sucess"));

        assertThat(driver.getCurrentUrl()).contains("login-sucess");
    }
}
