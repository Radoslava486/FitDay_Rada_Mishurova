package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


@Log4j2

public class ActivityLogPage extends HomePage {
    protected final By ACTIVITY_SEARCH = By.xpath("//input[@value='What did you do today?']");
    protected final By ACTIVITY_TIME_INPUT = By.xpath("//table//tr[1]//td[3]/input[@name='minutes']");
    protected final By ADD_TIME_BUTTON_LOCATOR = By.cssSelector("a.add.icon");
    protected final By ADD_TO_ACTIVITY_LOG_BUTTON = By.xpath("//span[contains(text(),'Add to Activity Log')]");
    protected final By FINAL_TABLE_TIME_OUTPUT = By.xpath("//table[@class='ib-list']//td[@class='time']");
    protected final By FINAL_TABLE_TIME_INPUT = By.xpath("//table[@class='ib-list']//input[@name='minutes']");
    protected final By FINAL_TABLE_CALORIES = By.xpath("//table[@class='ib-list']//tr[1]//td[6][@class='calories']");
    protected final By FINAL_TABLE_ACTIVITY = By.xpath("//table[@class='ib-list']//tr[1]//td[1][@class='name']");
    protected final By NO_RESULTS_WINDOW = By.xpath("//div[contains(text(),'No results')]");
    protected final By EDIT_ICON_LOCATOR = By.cssSelector("a.edit.icon");
    protected final By SAVE_BUTTON = By.xpath("//a[contains(text(),'Save')]");
    protected final By DELETE_ICON_LOCATOR = By.cssSelector("a.button-icon.delete");
    protected final By NO_DATA_ROW_LOCATOR = By.xpath("//td[contains(text(),'There is no data')]");

    public ActivityLogPage(WebDriver driver) {
        super(driver);
    }

    @Step("Searching for activity")
    public void searchActivity(String activity) {
        log.info(String.format("Searching for activity: %s", activity));
        driver.findElement(ACTIVITY_SEARCH).sendKeys(activity);
    }

    public boolean isNoResultsDisplayed() {
        return driver.findElement(NO_RESULTS_WINDOW).isDisplayed();
    }

    @Step("Waiting for page loaded")
    public void waitForSearchInputLoaded() {
        log.info("Waiting for page loaded");
        waitForElementDisplayed(ACTIVITY_SEARCH);
    }

    @Step("Waiting for page loaded")
    public void waitForCalculationLoaded() {
        log.info("Waiting for page loaded");
        waitForElementDisplayed(ADD_TO_ACTIVITY_LOG_BUTTON);
    }

    @Step("Clicking 'Add' to activity log")
    public void addToActivityLog() {
        log.info("Clicking 'Add' to activity log");
        driver.findElement(ADD_TO_ACTIVITY_LOG_BUTTON).click();
    }

    @Step("Setting activity time")
    public void setActivityTime(String time) {
        log.info(String.format("Setting activity time = %s", time));
        jsSetValue(driver.findElement(ACTIVITY_TIME_INPUT), time);

    }

    public void clickAdd() {
        jsClick(driver.findElement(ADD_TIME_BUTTON_LOCATOR));
    }

    public String getFinalActivityName() {
        return driver.findElement(FINAL_TABLE_ACTIVITY).getAttribute("textContent");
    }

    public String getFinalCalories() {
        return driver.findElement(FINAL_TABLE_CALORIES).getText();
    }

    @Step("Removing activity from log")
    public void removeActivityFromLog() {
        log.info("Removing activity from log");
        clickEditActivityLog();
        driver.findElement(DELETE_ICON_LOCATOR).click();
    }

    @Step("Clicking 'Edit' activity log")
    public void clickEditActivityLog() {
        log.info("Clicking 'Edit' activity log");
        driver.findElement(EDIT_ICON_LOCATOR).click();
    }

    @Step("Setting new time value in activity log")
    public void editTimeInActivityLog(String newTime, String newCalories) {
        log.info(String.format("Setting new time value = %s in activity log", newTime));
        WebElement timeField = driver.findElement(FINAL_TABLE_TIME_INPUT);
        jsSetValue(timeField, newTime);
        driver.findElement(SAVE_BUTTON).click();
        fluentWait(FINAL_TABLE_CALORIES, newCalories);
    }

    public String getNewTime() {
        return driver.findElement(FINAL_TABLE_TIME_OUTPUT).getAttribute("innerText");
    }

    public boolean isTableEmpty() {
        return driver.findElement(NO_DATA_ROW_LOCATOR).isDisplayed();
    }
}
