package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = "input[data-qa='login[email]']")
    private WebElement emailInput;

    @FindBy(css = "input[data-qa='login[password]']")
    private WebElement passInput;

    @FindBy(css = "button[data-qa='loginButtonSubmit']")
    private WebElement registerBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Signs in.
     */
    @Step("Log in with {email} and password")
    public void login(String email, String pass) {
        waitForVisibilityOf(emailInput);
        sendKeys(emailInput, email);
        sendKeys(passInput, pass);
        registerBtn.click();
    }
}
