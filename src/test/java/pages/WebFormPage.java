package pages;

import configs.TestPropertiesConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebFormPage extends BasePage {
    TestPropertiesConfig configProperties = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    @FindBy(id = "my-text-id")
    private WebElement textInput;
    @FindBy(name = "my-password")
    private WebElement password;

    public WebFormPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        driver.get(configProperties.getBaseUrl() + "web-form.html");
    }

    @Step("Get textInput field")
    public WebElement getTextInput() {
        return textInput;
    }

    @Step("Input text to TextInput")
    public void inputTextInput() {
        textInput.sendKeys(configProperties.getLogin());
    }

    @Step("Get entered text to TextInput")
    public String getEnteredTextTextInput() {
        String enteredText = configProperties.getLogin();
        return enteredText;
    }

    @Step("Get password field")
    public WebElement getPassword() {
        return password;
    }

    @Step("Input text to Password")
    public void inputPassword() {
        password.sendKeys(configProperties.getPassword());
    }

    @Step("Get entered text to Password")
    public String getEnteredTextPassword() {
        String enteredText = configProperties.getPassword();
        return enteredText;
    }
}
