package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.function.Function;

@Log4j2

public class FoodLogPage extends HomePage {

    protected final By EDIT_ICON_LOCATOR = By.cssSelector("a.edit.icon");
    protected final By DELETE_ICON_LOCATOR = By.cssSelector("a.button-icon.delete");
    protected final By NO_RESULTS_WINDOW = By.xpath("//div[contains(text(),'No results')]");
    protected final By FOOD_SEARCH = By.xpath("//input[@value='What did you eat today?']");
    protected final By ADD_FOOD_BUTTON_LOCATOR = By.cssSelector("a.add.icon");
    protected final By FOOD_ALERT = By.id("notice");
    protected final By SAVE_BUTTON = By.xpath("//a[contains(text(),'Save')]");
    protected final By FOOD_AMOUNT_FINAL_TABLE = By.xpath("//table[@class='ib-list']//input[@name = 'amount']");
    protected final By TABLE_CALORIES_LOCATOR = By.xpath("//table[@class='ib-list']//tr[1]//td[4][@class='calories']");
    protected final By TABLE_NAME_LOCATOR = By.xpath("//table[@class='ib-list']//tr[1]//td[1][@class='name']");
    protected final By NO_DATA_ROW_LOCATOR = By.xpath("//td[contains(text(),'There is no data')]");

    public FoodLogPage(WebDriver driver) {
        super(driver);
    }

    public void searchFood(String food) {
        log.info("Searching for food");
        driver.findElement(FOOD_SEARCH).sendKeys(food);
    }

    public void waitForSearchInputLoaded() {
        log.info("Waiting for page loaded");
        waitForElementDisplayed(FOOD_SEARCH);
    }

    public void clickAdd() {
        log.info("Clicking 'Add' food to log");
        jsClick(driver.findElement(ADD_FOOD_BUTTON_LOCATOR));
    }

    public void waitForTableLoaded() {
        log.info("Waiting for page loaded");
        waitForElementDisplayed(ADD_FOOD_BUTTON_LOCATOR);
    }

    public boolean isAlertDisplayed() {
        return driver.findElement(FOOD_ALERT).isDisplayed();
    }

    public boolean isTableEmpty() {
        return driver.findElement(NO_DATA_ROW_LOCATOR).isDisplayed();
    }

    public String getFinalFoodName() {
        System.out.println(driver.findElement(TABLE_NAME_LOCATOR).getAttribute("textContent"));
        return driver.findElement(TABLE_NAME_LOCATOR).getAttribute("textContent");
    }

    public String getFinalCalories() {
        return driver.findElement(TABLE_CALORIES_LOCATOR).getAttribute("innerText");
    }

    public String getNewAmount() {
        return driver.findElement(FOOD_AMOUNT_FINAL_TABLE).getAttribute("value");
    }

    public boolean isNoResultsDisplayed() {
        return driver.findElement(NO_RESULTS_WINDOW).isDisplayed();
    }

    public void removeFoodFromLog() {
        log.info("Removing food from log");
        editFoodLog();
        driver.findElement(DELETE_ICON_LOCATOR).click();
    }

    public void editFoodLog() {
        log.info("Click 'Edit' food log");
        driver.findElement(EDIT_ICON_LOCATOR).click();
    }

    public void editFoodAmountInFoodLog(String newValue, String newCalories) {
        log.info(String.format("Setting new amount value = %s in food log", newValue));
        WebElement amountField = driver.findElement(FOOD_AMOUNT_FINAL_TABLE);
        jsSetValue(amountField, newValue);
        driver.findElement(SAVE_BUTTON).click();
        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class);
        wait.until((Function<WebDriver, Boolean>) driver -> driver.findElement(TABLE_CALORIES_LOCATOR).getAttribute("innerText").equals(newCalories));
    }
}





