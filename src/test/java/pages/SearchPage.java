package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage {

    @FindAll({@FindBy(css = "div[data-qa='itemProductWidgets']")})
    private List<WebElement> productWidgets;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Returns the products count.
     */
    @Step("Return the products count")
    public int returnProductsCountOnThePage() {
        waitForVisibilityOf(productWidgets.get(0));
        return productWidgets.size();
    }
}
