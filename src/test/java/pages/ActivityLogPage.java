package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


@Log4j2

public class ActivityLogPage extends HomePage{
    public ActivityLogPage(WebDriver driver) {
        super(driver);
    }

    protected final By activitySearch = By.xpath("//input[@value='What did you do today?']");
    protected final By activityTimeInput = By.xpath("//table//tr[1]//td[3]/input[@name='minutes']");
    protected final By addTimeButtonLocator = By.cssSelector("a.add.icon");
    protected final static String CALORIES_COUNT_LOCATOR = "//*[contains(text(),'%s')]//following-sibling::td[@class = 'calories']";
    protected final By ADD_TO_ACTIVITY_LOG_BUTTON = By.xpath("//span[contains(text(),'Add to Activity Log')]");
    protected final By SAVE_BUTTON = By.xpath("//span[contains(text(),'Save')]");
    protected final By FINAL_TABLE_CALORIES = By.xpath("//table[@class='ib-list']//tr[1]//td[6][@class='calories']");
    protected final By FINAL_TABLE_ACTIVITY = By.xpath("//table[@class='ib-list']//tr[1]//td[1][@class='name']");
    protected final By NO_RESULTS_WINDOW = By.xpath("//div[contains(text(),'No results')]");
    public void searchActivity(String activity){
        log.info("Searching for activity");
        driver.findElement(activitySearch).sendKeys(activity);
    }

    public boolean isNoResultsDisplayed() {
        return driver.findElement(NO_RESULTS_WINDOW).isDisplayed();
    }
    public void waitForSearchInputLoaded() {
        log.info("Waiting for page loaded");
        waitForElementDisplayed(activitySearch);
    }

    public void waitForCalculationLoaded() {
        log.info("Waiting for page loaded");
        waitForElementDisplayed(ADD_TO_ACTIVITY_LOG_BUTTON);
    }

    public void addToActivityLog(){
        driver.findElement(ADD_TO_ACTIVITY_LOG_BUTTON).click();
    }

    public void saveActivity(){
        jsClick(driver.findElement(SAVE_BUTTON));
    }

    public void setActivityTime(String time){
        log.info("Setting activity time");
        jsSetValue(driver.findElement(activityTimeInput), time);

    }

    public void clickAdd(){
        jsClick(driver.findElement(addTimeButtonLocator));
    }

    public int getStartCaloriesConsumption(String activity){
        String caloriesBurning = driver.findElement(By.xpath(String.format(CALORIES_COUNT_LOCATOR,activity))).getAttribute("textContent");
        int caloriesBurningNumber = Integer.parseInt(caloriesBurning);
        return caloriesBurningNumber;
    }

    public int caloriesBurntCalculation(String time, String activity){
        getStartCaloriesConsumption(activity);
        int caloriesBurningNumber = 5;
        int timeNumber = Integer.parseInt(time);
        return  timeNumber*caloriesBurningNumber+1;
    }

    public String getCaloriesFromTable(String activity){
        return driver.findElement(By.xpath(String.format(CALORIES_COUNT_LOCATOR,activity))).getAttribute("textContent");
    }

    public String getFinalActivityName(){
        return driver.findElement(FINAL_TABLE_ACTIVITY).getText();
    }

    public String getFinalCalories(){
        return driver.findElement(FINAL_TABLE_CALORIES).getText();
    }
}
