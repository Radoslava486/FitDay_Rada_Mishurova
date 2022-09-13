package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.AllureUtils;
import utils.PropertyReader;

@Log4j2

public class LoginPage extends BasePage {

    private final static String URL = PropertyReader.getProperty("base_url");
    private final static By USERNAME_INPUT = By.cssSelector("input#username");
    private final static By PASSWORD_INPUT = By.cssSelector("input[name = Password]");
    private final static By LOGIN_BUTTON = By.cssSelector("input[name = login]");
    private final static By ERROR_MESSAGE = By.cssSelector("p.error");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Waiting for login page loaded")
    public void waitForPageLoaded() {
        log.info("Waiting for login page loaded");
        waitForElementDisplayed(USERNAME_INPUT);
    }
    @Step("Setting username")
    public void setUsername(String userName) {
        log.info(String.format("Setting username = %s", userName));
        driver.findElement(USERNAME_INPUT).sendKeys(userName);
    }
    @Step("Setting password")
    public void setPassword(String password) {
        log.info(String.format("Setting password = %s", password));
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }
    @Step("Clicking 'Login' button")
    public void clickLoginButton() {
        log.info("Clicking 'Login' button");
        driver.findElement(LOGIN_BUTTON).click();
    }
    @Step("Logging in")
    public void login(String username, String password) {
        log.info("Logging in");
        setUsername(username);
        setPassword(password);
        clickLoginButton();
        AllureUtils.attachScreenshot(driver);
    }

    public void open() {
        driver.get(URL);
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(ERROR_MESSAGE).isDisplayed();
    }

    public String getErrorMessageText() {

        return driver.findElement(ERROR_MESSAGE).getText();
    }
}

