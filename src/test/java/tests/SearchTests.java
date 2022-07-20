package tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchPage;

public class SearchTests extends BasicTest {

    private HomePage homePage;

    /**
     * Sets and loads test properties.
     */
    @BeforeTest
    public void doBeforeTest() {
        initializeProperties();
        loadPropertiesFromFile();
    }

    /**
     * Opens Browser, goes to start URL, accepts cookies.
     * Opens Home page and signs in.
     *
     * @param browser  can be chosen via parameter and value from testng xml.
     * @param headless can be chosen via parameter and value from testng xml.
     */
    @Parameters({"browser", "headless"})
    @BeforeMethod
    public void openBrowserAndAcceptCookiesAndSignIn(String browser, boolean headless) {

        //Prepare web drivers:
        doPreparationsFor(browser, headless);

        homePage = new HomePage(driver);
        defaultImplicitWait();
        homePage.acceptCookies();

        //Sign in:
        login();
    }

    @AfterMethod
    public void closeBrowser() {
        quitDriver();
    }

    @Test(description = "After logging in, User can find a product")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Story : Verify that after successful log in user can find some product on the website")
    public void checkSearchingAfterSignIn() {
        String product = testProperties.getProperty("product");
        int minimalCountOfExpectedProducts = Integer.parseInt(
                testProperties.getProperty("minimalCountOfExpectedProducts"));

        //Searches for a test data product
        homePage.searchFor(product);
        SearchPage searchPage = new SearchPage(driver);

        //Finds a count of found products on the first page
        int actualProductsCountOnThePage = searchPage.returnProductsCountOnThePage();

        //Expects at least 1 product on the Search page
        Assert.assertTrue(actualProductsCountOnThePage >= minimalCountOfExpectedProducts,
                "Product is not found");
    }

    private void login() {
        String email = testProperties.getProperty("email");
        String pass = testProperties.getProperty("pass");

        //Go to Log in Page:
        homePage.goToLogInPage();

        //Sign in with test data email and password:
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, pass);
    }
}
