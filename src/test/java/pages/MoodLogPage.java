package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@Log4j2

public class MoodLogPage extends HomePage{

    public MoodLogPage(WebDriver driver) {
        super(driver);
    }
    protected final By EDIT_BUTTON_LOCATOR = By.xpath("//span[contains(text(), 'Edit')]");
    protected final By SAVE_BUTTON_LOCATOR = By.xpath("//a[@class = 'save button']/span[contains(text(), 'Save')]");
    protected final By MOOD_DIARY_TEXTAREA_LOCATOR = By.xpath("//div[@class = 'text-wrapper']");
    @Step("Waiting for page loaded")
    public void waitForPageLoaded() {
        log.info("Waiting for page loaded");
        waitForElementDisplayed(EDIT_BUTTON_LOCATOR);
    }

    @Step("Clicking Edit button")
    public void clickEditButton() {
        log.info("Clicking 'Edit' button");
        jsClick(driver.findElement(EDIT_BUTTON_LOCATOR));
    }

    @Step("Editing mood diary")
    public void editMoodLog(String moodLog) {
        log.info(String.format("Setting mood log = %s", moodLog));
        driver.findElement(By.name("text")).sendKeys(moodLog);
    }

    @Step("Clicking Save button")
    public void clickSaveButton() {
        log.info("Clicking 'Save' button");
        driver.switchTo().defaultContent();
        jsClick(driver.findElement(SAVE_BUTTON_LOCATOR));
    }

    public String getMoodText() {
        return driver.findElement(MOOD_DIARY_TEXTAREA_LOCATOR).getText();
    }

    public void removeDataFromLog(){
        clickEditButton();
        driver.findElement(By.name("text")).clear();
        clickSaveButton();
    }













}

