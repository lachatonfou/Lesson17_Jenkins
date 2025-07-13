package ui;

import configs.TestConfig;
import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DZ8 {

    WebDriver driver;
    //TestConfig config = new TestConfig();
    //String BASE_URL = config.getBaseUrl();

    //Properties properties = new Properties();
    //String baseUrl = properties.getProperty("baseUrl");

    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get(config.getBaseUrl());
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void proverkaTextInput() throws InterruptedException {

        WebElement textInput = driver.findElement(By.id("my-text-id"));
        textInput.sendKeys(config.getUsername());
        String enteredValue = textInput.getAttribute("value");
        Thread.sleep(1000);
        assertEquals(config.getUsername(), enteredValue);

        WebElement textInputLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Text input']"));
        String textInputText = textInputLabel.getText();
        assertEquals("Text input", textInputText);
    }

    @Test
    void proverkaPassword() throws InterruptedException {

        WebElement password = driver.findElement(By.name("my-password"));
        password.sendKeys(config.getPassword());
        String enteredValue = password.getAttribute("value");
        Thread.sleep(1000);
        assertEquals(config.getPassword(), enteredValue);

        WebElement passwordLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Password']"));
        String passwordText = passwordLabel.getText();
        assertEquals("Password", passwordText);
    }

    @Test
    void proverkaTextArea() throws InterruptedException {

        WebElement textArea = driver.findElement(By.name("my-textarea"));
        textArea.sendKeys("uwuwu");
        String enteredValue = textArea.getAttribute("value");
        Thread.sleep(1000);
        assertEquals("uwuwu", enteredValue);

        WebElement textAreaLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Textarea']"));
        String textAreaText = textAreaLabel.getText();
        assertEquals("Textarea", textAreaText);
    }

    @Test
    void proverkaDisabledInput() throws InterruptedException {

        WebElement disabledInput = driver.findElement(By.name("my-disabled"));
        Assertions.assertFalse(disabledInput.isEnabled());
        Assertions.assertThrows(ElementNotInteractableException.class, () -> disabledInput.sendKeys("test"));

        String placeholderText = disabledInput.getAttribute("placeholder");
        Thread.sleep(1000);
        assertEquals("Disabled input", placeholderText);

        WebElement disabledInputLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Disabled input']"));
        String disabledInputText = disabledInputLabel.getText();
        assertEquals("Disabled input", disabledInputText);

    }

    @Test
    void proverkaReadonlyInput() throws InterruptedException {

        WebElement readonlyInput = driver.findElement(By.name("my-readonly"));
        Assertions.assertTrue(readonlyInput.isEnabled());

        String placeholderText = readonlyInput.getAttribute("value");
        Thread.sleep(1000);
        assertEquals("Readonly input", placeholderText);

        WebElement readonlyInputLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Readonly input']"));
        String readonlyInputText = readonlyInputLabel.getText();
        assertEquals("Readonly input", readonlyInputText);

    }

    @Test
    void proverkaDropdownSelect() throws InterruptedException {

        WebElement dropdownSelect = driver.findElement(By.name("my-select"));
        Select dropdown = new Select(dropdownSelect);
        dropdown.selectByVisibleText("Two");
        WebElement selectedOption1 = dropdown.getFirstSelectedOption();
        String selectedValue = selectedOption1.getText();
        Thread.sleep(3000);
        assertEquals("Two", selectedValue);

        dropdown.selectByValue("3");
        WebElement selectedOption2 = dropdown.getFirstSelectedOption();
        String selectedValue2 = selectedOption2.getText();
        assertEquals("Three", selectedValue2);
        Thread.sleep(3000);
    }

    @Test
    void proverkaCheckedCheckbox() throws InterruptedException {

        WebElement checkedCheckbox = driver.findElement(By.id("my-check-1"));
        checkedCheckbox.click();
        Thread.sleep(1000);
        boolean isChecked = checkedCheckbox.isSelected();
        WebElement checkedCheckboxLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Checked checkbox']"));
        String checkedCheckboxText = checkedCheckboxLabel.getText();
        assertEquals("Checked checkbox", checkedCheckboxText);
        assertEquals(false, isChecked);
    }

    @Test
    void proverkaDefaultCheckbox() throws InterruptedException {

        WebElement defaultCheckbox = driver.findElement(By.id("my-check-2"));
        defaultCheckbox.click();
        Thread.sleep(1000);
        boolean isChecked = defaultCheckbox.isSelected();
        WebElement checkedCheckboxLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Default checkbox']"));
        String checkedCheckboxText = checkedCheckboxLabel.getText();
        assertEquals("Default checkbox", checkedCheckboxText);
        assertEquals(true, isChecked);
    }

    @Test
    void proverkaCheckedRadio() throws InterruptedException {

        WebElement checkedRadio = driver.findElement(By.id("my-radio-1"));
        Thread.sleep(1000);
        boolean isChecked = checkedRadio.isSelected();
        WebElement checkedCheckboxLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Checked radio']"));
        String checkedCheckboxText = checkedCheckboxLabel.getText();
        assertEquals("Checked radio", checkedCheckboxText);
        assertEquals(true, isChecked);
    }

    @Test
    void proverkaDefaultRadio() throws InterruptedException {

        WebElement defaultRadio = driver.findElement(By.id("my-radio-2"));
        defaultRadio.click();
        Thread.sleep(1000);
        boolean isSelected = defaultRadio.isSelected();
        WebElement checkedCheckboxLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Default radio']"));
        String checkedCheckboxText = checkedCheckboxLabel.getText();
        assertEquals("Default radio", checkedCheckboxText);
        assertEquals(true, isSelected);
    }

    @Test
    void proverkaColorPicker() throws InterruptedException {

        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        colorPicker.sendKeys("#ff0099");
        Thread.sleep(1000);
        String colorPickerValue = colorPicker.getAttribute("value");
        WebElement colorPickerLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Color picker']"));
        String colorPickerText = colorPickerLabel.getText();
        assertEquals("Color picker", colorPickerText);
        assertEquals("#ff0099", colorPickerValue);
    }

    @Test
    void proverkaDatePicker() throws InterruptedException {

        WebElement datePicker = driver.findElement(By.xpath("//input[@class='form-control' and @name='my-date']"));
        datePicker.sendKeys("13/04/2025");
        Thread.sleep(1000);
        String datePickerValue = datePicker.getAttribute("value");
        WebElement datePickerLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Date picker']"));
        String datePickerText = datePickerLabel.getText();
        assertEquals("Date picker", datePickerText);
        assertEquals("13/04/2025", datePickerValue);
    }

    @Test
    void proverkaExampleRange() throws InterruptedException {

        WebElement exampleRange = driver.findElement(By.xpath("//input[@class='form-range' and @name='my-range']"));
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.clickAndHold(exampleRange)
                .moveByOffset(60, 0)
                .release()
                .perform();
        Thread.sleep(1000);
        String exampleRangeValue = exampleRange.getAttribute("value");
        WebElement exampleRangeLabel = driver.findElement(By.xpath("//label[normalize-space(.)='Example range']"));
        String exampleRangeText = exampleRangeLabel.getText();
        assertEquals("Example range", exampleRangeText);
        assertEquals("7", exampleRangeValue);
    }
}
