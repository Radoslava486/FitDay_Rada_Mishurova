package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@Log4j2

public class WeightLogPage extends HomePage{

    public WeightLogPage(WebDriver driver) {
        super(driver);
    }

    protected final By EDIT_BUTTON_LOCATOR = By.xpath("//td[@class = 'weight-current']//span[contains(text(), 'Edit')]");
    protected final By WEIGHT_PARAMETER_LOCATOR = By.xpath("//input[@name = 'weight']");
    protected final By SAVE_BUTTON_LOCATOR = By.xpath("//span[contains(text(), 'Save')]");
    protected final By WEIGHT_INFO_LOCATOR = By.xpath("//td[@class='weight-current']//span[@class='fd-highlight']");
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

    @Step("Clicking Save button")
    public void clickSaveButton() {
        log.info("Clicking 'Save' button");
        jsClick(driver.findElement(SAVE_BUTTON_LOCATOR));
    }

    @Step("Setting weight parameter")
    public void setWeightParameter(String weightParameter) {
        log.info(String.format("Setting weight parameter = %s", weightParameter));
        jsSetValue(driver.findElement(WEIGHT_PARAMETER_LOCATOR), weightParameter);
    }
    public String getWeightParameter() {
        return driver.findElement(WEIGHT_INFO_LOCATOR).getAttribute("innerText");
    }
}
