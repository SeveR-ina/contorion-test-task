package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Home page of a web-site.
 */
public class HomePage extends BasePage {
    @FindBy(css = "div[data-class='hover'] a span")
    private WebElement accHeader;

    @FindBy(id = "search-input")
    private WebElement searchInput;

    @FindBy(id = "search-button")
    private WebElement searchBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Goes to the Login page.
     */
    @Step("Go to the Log in page")
    public void goToLogInPage() {
        waitForVisibilityOf(accHeader);
        accHeader.click();
    }

    /**
     * Searches for some product.
     */
    @Step("Search for a {criteria} on the web-site")
    public void searchFor(String criteria) {
        waitForVisibilityOf(searchInput);
        sendKeys(searchInput, criteria);
        searchBtn.click();
    }
}
