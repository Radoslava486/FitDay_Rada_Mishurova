package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
@Log4j2

public class BodyLogPage extends HomePage {

    public BodyLogPage(WebDriver driver){
        super(driver);
    }

    protected final By CHEST_PARAMETER_LOCATOR = By.name("chest");
    protected final By WAIST_PARAMETER_LOCATOR = By.name("waist");
    protected final By HIPS_PARAMETER_LOCATOR = By.name("hips");
    protected final By SAVE_BUTTON_LOCATOR = By.xpath("//span[contains(text(), 'Save')]");
    protected final By EDIT_BUTTON_LOCATOR = By.xpath("//span[contains(text(), 'Edit')]");
    protected final By CHEST_INFO_LOCATOR = By.xpath("//table[@class='ib-list']//td[4]");
    protected final By WAIST_INFO_LOCATOR = By.xpath("//table[@class='ib-list']//td[5]");
    protected final By HIPS_INFO_LOCATOR = By.xpath("//table[@class='ib-list']//td[6]");
    @Step("Setting waist parameter")
    public void setWaistParameter(String waistParameter) {
        log.info(String.format("Setting waist parameter = %s", waistParameter));
        jsSetValue(driver.findElement(WAIST_PARAMETER_LOCATOR), waistParameter);
    }

    @Step("Setting chest parameter")
    public void setChestParameter(String chestParameter) {
        log.info(String.format("Setting chest parameter = %s", chestParameter));
        jsSetValue(driver.findElement(CHEST_PARAMETER_LOCATOR), chestParameter);
    }

    @Step("Setting hips parameter")
    public void setHipsParameter(String hipsParameter) {
        log.info(String.format("Setting hips parameter = %s", hipsParameter));
        jsSetValue(driver.findElement(HIPS_PARAMETER_LOCATOR), hipsParameter);
    }

    @Step("Clicking Save button")
    public void clickSaveButton() {
        log.info("Clicking 'Save' button");
        jsClick(driver.findElement(SAVE_BUTTON_LOCATOR));
    }

    @Step("Waiting for page loaded")
    public void waitForPageLoaded() {
        log.info("Waiting for page loaded");
        waitForElementDisplayed(SAVE_BUTTON_LOCATOR);
    }

    @Step("Waiting for data get saved")
    public void waitForDataSaved() {
        log.info("Waiting for data get saved");
        waitForElementDisplayed(EDIT_BUTTON_LOCATOR);
    }

    public String getChestParameter() {
        return driver.findElement(CHEST_INFO_LOCATOR).getAttribute("innerText");
    }

    public String getHipsParameter() {
        return driver.findElement(HIPS_INFO_LOCATOR).getAttribute("innerText");
    }

    public String getWaistParameter() {
        return driver.findElement(WAIST_INFO_LOCATOR).getAttribute("innerText");
    }

    public void removeDataFromLog(){
        jsClick(driver.findElement(EDIT_BUTTON_LOCATOR));
        setHipsParameter("0");
        setWaistParameter("0");
        setChestParameter("0");
        clickSaveButton();
    }



}
