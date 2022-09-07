package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


@Log4j2

public class ActivityLogPage extends HomePage{
    public ActivityLogPage(WebDriver driver) {
        super(driver);
    }

    protected final By ACTIVITY_SEARCH = By.xpath("//input[@value='What did you do today?']");
    protected final By ACTIVITY_TIME_INPUT = By.xpath("//table//tr[1]//td[3]/input[@name='minutes']");
    protected final By ADD_TIME_BUTTON_LOCATOR = By.cssSelector("a.add.icon");
    protected final static String CALORIES_COUNT_LOCATOR = "//*[contains(text(),'%s')]//following-sibling::td[@class = 'calories']";
    protected final By ADD_TO_ACTIVITY_LOG_BUTTON = By.xpath("//span[contains(text(),'Add to Activity Log')]");
    protected final By FINAL_TABLE_TIME = By.xpath("//table[@class='ib-list']//input[@name='minutes']");
    protected final By FINAL_TABLE_CALORIES = By.xpath("//table[@class='ib-list']//tr[1]//td[6][@class='calories']");
    protected final By FINAL_TABLE_ACTIVITY = By.xpath("//table[@class='ib-list']//tr[1]//td[1][@class='name']");
    protected final By NO_RESULTS_WINDOW = By.xpath("//div[contains(text(),'No results')]");
    protected final By EDIT_ICON_LOCATOR = By.cssSelector("a.edit.icon");
    protected final By SAVE_BUTTON = By.xpath("//a[contains(text(),'Save')]");
    protected final By DELETE_ICON_LOCATOR = By.cssSelector("a.button-icon.delete");
    protected final By NO_DATA_ROW_LOCATOR = By.xpath("//td[contains(text(),'There is no data')]");
    public void searchActivity(String activity){
        log.info(String.format("Searching for activity: %s", activity));
        driver.findElement(ACTIVITY_SEARCH).sendKeys(activity);
    }

    public boolean isNoResultsDisplayed() {
        return driver.findElement(NO_RESULTS_WINDOW).isDisplayed();
    }
    public void waitForSearchInputLoaded() {
        log.info("Waiting for page loaded");
        waitForElementDisplayed(ACTIVITY_SEARCH);
    }

    public void waitForCalculationLoaded() {
        log.info("Waiting for page loaded");
        waitForElementDisplayed(ADD_TO_ACTIVITY_LOG_BUTTON);
    }

    public void addToActivityLog(){
        driver.findElement(ADD_TO_ACTIVITY_LOG_BUTTON).click();
    }

    public void setActivityTime(String time){
        log.info("Setting activity time");
        jsSetValue(driver.findElement(ACTIVITY_TIME_INPUT), time);

    }

    public void clickAdd(){
        jsClick(driver.findElement(ADD_TIME_BUTTON_LOCATOR));
    }

    public String getCaloriesFromTable(String activity){
        return driver.findElement(By.xpath(String.format
                (CALORIES_COUNT_LOCATOR,activity))).getAttribute("textContent");
    }

    public String getFinalActivityName(){
        return driver.findElement(FINAL_TABLE_ACTIVITY).getAttribute("textContent");
    }

    public String getFinalCalories(){
        return driver.findElement(FINAL_TABLE_CALORIES).getText();
    }

    public void removeActivityFromLog() {
        editActivityLog();
        driver.findElement(DELETE_ICON_LOCATOR).click();
    }
    public void editActivityLog() {
        driver.findElement(EDIT_ICON_LOCATOR).click();
    }

    public void editTimeInActivityLog(String newTime) {
        WebElement timeField = driver.findElement(FINAL_TABLE_TIME);
        jsSetValue(timeField, newTime);
        driver.findElement(SAVE_BUTTON).click();
    }
    public String getNewTime(){
        return driver.findElement(FINAL_TABLE_TIME).getAttribute("value");}

    public boolean isTableEmpty(){
        return driver.findElement(NO_DATA_ROW_LOCATOR).isDisplayed();
    }
}
