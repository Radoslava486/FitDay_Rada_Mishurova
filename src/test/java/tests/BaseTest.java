package tests;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.FoodLogPage;
import pages.HomePage;
import pages.ActivityLogPage;
import pages.LoginPage;
import utils.DriverFactory;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {
    protected final static String USERNAME = PropertyReader.getProperty("username");
    protected final static String PASSWORD = PropertyReader.getProperty("password");
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected ActivityLogPage logPage;
    protected FoodLogPage foodLogPage;

    @BeforeClass(alwaysRun = true)
    public void setUp(ITestContext testContext) throws Exception {
        String browserName = System.getProperty("browser", "chrome");
        driver = DriverFactory.getDriver(browserName);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        testContext.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        logPage = new ActivityLogPage(driver);
        foodLogPage = new FoodLogPage(driver);
    }

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        loginPage.open();
    }


    @AfterMethod(alwaysRun = true)
    public void clearCookies() {
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript(String.format("window.localStorage.clear();"));
        ((JavascriptExecutor) driver).executeScript(String.format("window.sessionStorage.clear();"));
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}



