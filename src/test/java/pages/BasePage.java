package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TimeOuts;

import java.time.Duration;

/**
 * Parent page for all pages, includes methods for working with elements.
 */
abstract class BasePage {
    protected WebDriver driver;
    private final Duration defaultTime;

    @FindBy(id = "popin_tc_privacy_button")
    private WebElement acceptCookiesBtn;

    /**
     * Constructor of Base page class.
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.defaultTime = Duration.ofSeconds(TimeOuts.DEFAULT_TIMEOUT_IN_SECONDS.getTimeOutValue());
        PageFactory.initElements(driver, this);
    }

    /**
     * Waits for visibility of the page element for default time.
     */
    @Step("Wait for visibility of element: {0}")
    protected void waitForVisibilityOf(WebElement webElement) {
        new WebDriverWait(driver, defaultTime).
                until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Fills the inputs with specific text.
     */
    @Step("Enter text: {text} to the input: {field}}")
    protected void sendKeys(WebElement field, String text) {
        field.click();
        field.clear();
        field.sendKeys(text);
    }

    /**
     * Clicks on accept cookies button.
     */
    @Step("Accept cookies")
    public void acceptCookies() {
        acceptCookiesBtn.click();
    }
}
